package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.AirlineInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirlineInfoRepository extends JpaRepository<AirlineInfo,Integer> {

    List<AirlineInfo> findByAirlineTypeIn(List<Integer> airlineTypeList);

}
