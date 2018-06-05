package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketInfoRepositoryTest {
    @Autowired
    private  TicketInfoRepository repository;

    @Test
    // @Transactional
    public  void saveTest(){
        TicketInfo ticketInfo=new TicketInfo();
        ticketInfo.setTicketId("8889");
        ticketInfo.setTicketDepart("北京");
        ticketInfo.setTicketPrice(new BigDecimal(45.45));
        ticketInfo.setTicketStock(10);
        ticketInfo.setTicketArrive("上海");
        ticketInfo.setAirlineType(0);

        repository.save(ticketInfo);

    }

}
