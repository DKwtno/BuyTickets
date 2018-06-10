package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerInfoRepository extends JpaRepository<BuyerInfo,Integer> {

    BuyerInfo findByBuyerOpenid(String buyerOpenid);
}
