package com.sorahjy.buytickets.service;

import com.sorahjy.buytickets.dto.OrderDTO;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String buyerOpenid,String orderId);

}
