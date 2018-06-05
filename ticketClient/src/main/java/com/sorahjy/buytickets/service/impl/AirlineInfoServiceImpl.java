package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dataobject.AirlineInfo;
import com.sorahjy.buytickets.repository.AirlineInfoRepository;
import com.sorahjy.buytickets.service.AirlineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineInfoServiceImpl implements AirlineInfoService {


    @Autowired
    private AirlineInfoRepository repository;

    @Override
    public AirlineInfo findOne(final Integer airlineId) {
        return repository.findById(airlineId).get();
    }

    @Override
    public List<AirlineInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public List<AirlineInfo> findByAirlineTypeIn(final List<Integer> airlineTypeList) {
        return repository.findByAirlineTypeIn(airlineTypeList);
    }

    @Override
    public AirlineInfo save(final AirlineInfo airlineInfo) {
        return save(airlineInfo);
    }
}
