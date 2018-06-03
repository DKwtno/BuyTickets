package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketInfoServiceImplTest {

    @Autowired
    private TicketInfoServiceImpl ticketInfoService;

    @Test
    public void findAll() throws Exception{
        PageRequest request=new PageRequest(0,2);
        Page<TicketInfo> ticketInfoPage=ticketInfoService.findAll(request);
        System.out.println(ticketInfoPage.getTotalElements());

    }

}
