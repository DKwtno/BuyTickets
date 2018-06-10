package com.sorahjy.buytickets.controller;


import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.enums.ResultEnum;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @CacheEvict(cacheNames = "order",key="123")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        PageRequest request=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(request);
         map.put("orderDTOPage",orderDTOPage);
         map.put("currentPage",page);
         map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO=orderService.findOne(orderId);
        }catch (SellException e){
            map.put("msg ",e.getMessage());
            map.put("url", "/tickets/seller/order/list");
            return new ModelAndView("commom/error");
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){

        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);

        }catch (SellException e){
            map.put("msg", ResultEnum.insufficient_privileges.getMesssage());
            map.put("url", "/tickets/seller/order/list");
            return new ModelAndView("common/error");
        }
        map.put("msg", "成功取消订单");
        map.put("url", "/tickets/seller/order/list");
        return new ModelAndView("common/success");
    }

}
