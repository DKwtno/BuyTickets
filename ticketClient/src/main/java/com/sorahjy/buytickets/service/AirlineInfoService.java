package com.sorahjy.buytickets.service;

import com.sorahjy.buytickets.dataobject.AirlineInfo;

import java.util.List;

public interface AirlineInfoService {
    AirlineInfo findOne(Integer airlineId);
    List<AirlineInfo> findAll();
    List<AirlineInfo> findByAirlineTypeIn(List<Integer> airlineTypeList);
    AirlineInfo save(AirlineInfo airlineInfo);
}
