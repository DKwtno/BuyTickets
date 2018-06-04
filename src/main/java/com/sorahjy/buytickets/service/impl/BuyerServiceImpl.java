package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.enums.ResultEnum;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.service.BuyerService;
import com.sorahjy.buytickets.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(final String buyerOpenid, final String orderId) {

        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null) return null;

        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(buyerOpenid)) {
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
