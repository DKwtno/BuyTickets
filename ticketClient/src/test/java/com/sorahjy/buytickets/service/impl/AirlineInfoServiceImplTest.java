package com.sorahjy.buytickets.service.impl;

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
public class AirlineInfoServiceImplTest {

    @Autowired
    private AirlineInfoServiceImpl airlineInfoService;

    @Test
    public void findOne() {
        AirlineInfo airlineInfo=airlineInfoService.findOne(1);
        Assert.assertEquals(new Integer(1),airlineInfo.getAirlineId());
    }

    @Test
    public void findAll() {
        List<AirlineInfo> airlineInfoList=airlineInfoService.findAll();
        Assert.assertNotEquals(0,airlineInfoList.size());
    }

    @Test
    public void findByAirlineType() {
        List<AirlineInfo> airlineInfoList=airlineInfoService.findByAirlineTypeIn(Arrays.asList(0,888));
        Assert.assertNotEquals(0,airlineInfoList.size());
    }

    @Test
    @Transactional
    public void save(){
        AirlineInfo airlineInfo=new AirlineInfo("新的机票",44);
        Assert.assertNotNull(airlineInfo);
    }
}
