package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dataobject.OrderDetail;
import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.enums.OrderStatusEnum;
import com.sorahjy.buytickets.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String  BUYEROPENID="110110";

    private final String ORDER_ID="1528033214716995571";

    @Test
    @Transactional
    public void create() {

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("hjy");
        orderDTO.setBuyerPhone("18080808080");
        orderDTO.setBuyerOpenid(BUYEROPENID);

        //cart
        OrderDetail o1=new OrderDetail();
        o1.setTicketId("1234");
        o1.setTicketQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setTicketId("8888");
        o2.setTicketQuantity(2);
        orderDTO.setOrderDetailList(Arrays.asList(o1,o2));

        OrderDTO result=orderService.create(orderDTO);

        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() {
        OrderDTO result=orderService.findOne(ORDER_ID);
        Assert.assertNotNull(result);
    }

    @Test
    public void findList() {
        PageRequest request=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYEROPENID,request);
        System.out.println(orderDTOPage.toString());
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
    }

    @Test
    @Transactional
    public void finish() {
        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.finish(orderDTO);
        Assert.assertEquals(result.getOrderStatus(),OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    @Transactional
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(result.getPayStatus(), PayStatusEnum.SUCCESS.getCode());
    }
}
