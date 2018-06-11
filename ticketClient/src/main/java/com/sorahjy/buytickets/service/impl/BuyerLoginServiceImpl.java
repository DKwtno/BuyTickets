package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dataobject.BuyerInfo;
import com.sorahjy.buytickets.repository.BuyerInfoRepository;
import com.sorahjy.buytickets.service.BuyerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerLoginServiceImpl implements BuyerLoginService {

    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Override
    public BuyerInfo findBuyerInfoByBuyerOpenid(final String buyerOpenid) {

        return buyerInfoRepository.findByBuyerOpenid(buyerOpenid);
    }

    @Override
    public BuyerInfo save(final BuyerInfo buyerInfo) {
        return buyerInfoRepository.save(buyerInfo);
    }
}
