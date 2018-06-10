package com.sorahjy.buytickets.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sorahjy.buytickets.dataobject.OrderDetail;
import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.enums.ResultEnum;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO=new OrderDTO();
        Gson gson=new Gson();

        orderDTO.setBuyerName(orderForm.getBuyerName());
        orderDTO.setBuyerPhone(orderForm.getBuyerPhone());
        orderDTO.setBuyerOpenid(orderForm.getBuyerOpenid());

        List<OrderDetail> orderDetailList=new ArrayList<>();

        try {

            orderDetailList=gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() { }.getType());

        }catch (Exception e ){
            log.error("对象转换错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
