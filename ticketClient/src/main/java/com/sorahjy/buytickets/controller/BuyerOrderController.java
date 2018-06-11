package com.sorahjy.buytickets.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sorahjy.buytickets.VO.ResultVO;
import com.sorahjy.buytickets.converter.OrderForm2OrderDTOConverter;
import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.enums.ResultEnum;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.form.OrderForm;
import com.sorahjy.buytickets.service.BuyerService;
import com.sorahjy.buytickets.service.OrderService;
import com.sorahjy.buytickets.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.error("订单参数不对 orderForm={}",orderForm);
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());

        }
        OrderDTO orderDTO=OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("购物车空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);    //TODO
    }

    //订单列表

    private String defaultFallback(){
        return "太拥挤了 ，请稍后再试";
    }


    @GetMapping("/test")
    @HystrixCommand(commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
    })
    public String getTest(){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("测试：超时错误");
            // e.printStackTrace();
        }

        return "OK";
    }

    @GetMapping("/list")
    @Cacheable(cacheNames = "buyer",key = "orderlist")
    public ResultVO<List<OrderDTO>> list(@RequestParam("buyerOpenid") String buyerOpenid, @RequestParam(value="page",defaultValue = "0")Integer page,@RequestParam(value="size",defaultValue = "10")Integer size) {


        if (StringUtils.isEmpty(buyerOpenid)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, request);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("buyerOpenid")String buyerOpenid,@RequestParam("orderId")String orderId) {

        //TODO 没有验证用户 buyerOpenid

        OrderDTO orderDTO=buyerService.findOrderOne(buyerOpenid,orderId);
        // if(orderDTO.getBuyerOpenid().equalsIgnoreCase(buyerOpenid)){
        //
        // }

        return ResultVOUtil.success(orderDTO);
    }

    //TODO 取消订单


}
