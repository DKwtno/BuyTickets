package com.sorahjy.buytickets.service;

import com.sorahjy.buytickets.dataobject.BuyerInfo;

public interface BuyerLoginService {

    public BuyerInfo findBuyerInfoByBuyerOpenid(String buyerOpenid);

    BuyerInfo save(BuyerInfo buyerInfo);

}
