package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerName("hjy");
        orderMaster.setBuyerPhone("18080808080");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.4));
        orderMaster.setOrderId("123");

        OrderMaster result=repository.save(orderMaster);
        Assert.assertNotNull(result);

    }

    @Test
    public void insert(){
        // PageRequest request=new PageRequest(0,3);
        // Page<OrderMaster> result=repository.findByBuyerOpenid("110110",request);
        // System.out.println(result.getTotalElements());
    }

}
