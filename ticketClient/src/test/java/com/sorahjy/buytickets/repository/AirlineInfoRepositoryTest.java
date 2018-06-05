package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.AirlineInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirlineInfoRepositoryTest {

    @Autowired
    private AirlineInfoRepository repository;

    @Test
    public void findOneTest() {
        AirlineInfo ticketCatagory = repository.findById(1).get();
        System.out.println(ticketCatagory);
    }



    @Test
    @Transactional
    public void saveTest() {

        AirlineInfo ticketCatagory = new AirlineInfo("新的机票",3);
        repository.save(ticketCatagory);
        AirlineInfo result=repository.save(ticketCatagory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByAirlineTypeInTest(){
        List<Integer> list= Arrays.asList(0,888,2);
        List<AirlineInfo> result=repository.findByAirlineTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}
