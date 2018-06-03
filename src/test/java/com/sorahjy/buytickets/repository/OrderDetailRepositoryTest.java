package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("23454");
        orderDetail.setOrderId("123");
        orderDetail.setTicketId("1234");
        orderDetail.setTicketDepart("上海");
        orderDetail.setTicketArrive("东京");
        orderDetail.setTicketPrice(new BigDecimal(34.34));
        orderDetail.setTicketQuantity(1);
        OrderDetail result=repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

}
