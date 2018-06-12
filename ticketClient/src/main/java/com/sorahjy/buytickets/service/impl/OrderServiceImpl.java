package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.converter.OrderMaster2OrderDTOconverter;
import com.sorahjy.buytickets.dataobject.OrderDetail;
import com.sorahjy.buytickets.dataobject.OrderMaster;
import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.dto.CartDTO;
import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.enums.OrderStatusEnum;
import com.sorahjy.buytickets.enums.PayStatusEnum;
import com.sorahjy.buytickets.enums.ResultEnum;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.repository.OrderDetailRepository;
import com.sorahjy.buytickets.repository.OrderMasterRepository;
import com.sorahjy.buytickets.service.OrderService;
import com.sorahjy.buytickets.service.RedisLock;
import com.sorahjy.buytickets.service.TicketInfoService;
import com.sorahjy.buytickets.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final int TIMEOUT=1000*5;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private TicketInfoService ticketInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
    public OrderDTO create(final OrderDTO orderDTO) {

        String orderId= orderDTO.getOrderId();

        if(orderId==null)
            orderId= KeyUtil.genUniqueKey();

        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

        //jdk7
        // List<CartDTO> cartDTOList=new ArrayList<>();

        //1. get ticketid && 数量
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            TicketInfo ticketInfo=ticketInfoService.findOne(orderDetail.getTicketId());
            if(ticketInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算总价
            orderAmount=ticketInfo.getTicketPrice()
                .multiply(new BigDecimal(orderDetail.getTicketQuantity()))
                .add(orderAmount);
            //3. detail入库
            //补充缺失属性
            BeanUtils.copyProperties(ticketInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);

            //jdk7
            // CartDTO cartDTO=new CartDTO(orderDetail.getTicketId(),orderDetail.getTicketQuantity());
            // cartDTOList.add CartDTO

        }

        //4.写入订单数据库
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);


        //5.扣库存
        //jdk8
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
            new CartDTO(e.getTicketId(),e.getTicketQuantity())
        ).collect(Collectors.toList());
        ticketInfoService.decreaseStock(cartDTOList);

        //返回orderDTO

        return orderDTO;
    }

    @Override
    public OrderDTO searchOne(final String orderId) {

        long time=System.currentTimeMillis()+TIMEOUT;

        if(!redisLock.lock(orderId,String.valueOf(time)))
            throw new SellException(444,"服务忙，请稍后再试");
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList=orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        redisLock.unlock(orderId,String.valueOf(time));

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(final String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }


    @Override
    public Page<OrderDTO> findList(final String buyerOpenid, final Pageable pageable) {

        Page<OrderMaster> orderMasterPage=orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList=OrderMaster2OrderDTOconverter.convert(orderMasterPage.getContent());


        Page<OrderDTO> orderDTOPage=new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }



    @Override
    public OrderDTO cancel(final OrderDTO orderDTO) {
        OrderMaster orderMaster=new OrderMaster();


        //判断订单状态

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("只有新订单可以取消");
            throw new SellException(ResultEnum.UNKNOWN_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //返回库存

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream()
            .map(e->new CartDTO(e.getTicketId(),e.getTicketQuantity()))
            .collect(Collectors.toList());

        ticketInfoService.increaseStock(cartDTOList);


        //TODO 如果已支付，需要退款

        return orderDTO;
    }



    @Override
    public OrderDTO finish(final OrderDTO orderDTO) {

        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            //TODO errorHandler
            throw new SellException(ResultEnum.UNKNOWN_ERROR);
        }


        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            //TODO errorHandler

            throw new SellException(ResultEnum.UNKNOWN_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO paid(final OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.UNKNOWN_ERROR);
            //TODO error
        }

        //判断支付状态

        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO error
            throw new SellException(ResultEnum.UNKNOWN_ERROR);
        }

        //修改支付状态

        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            //TODO error
            throw new SellException(ResultEnum.UNKNOWN_ERROR);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(final Pageable pageable) {

        Page<OrderMaster> orderMasterPage=orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOconverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    public OrderMaster save(final OrderMaster orderMaster) {
        return orderMasterRepository.save(orderMaster);
    }

    @Override
    public OrderMaster findById(final String orderId) {
        return orderMasterRepository.findById(orderId).get();
    }
}
