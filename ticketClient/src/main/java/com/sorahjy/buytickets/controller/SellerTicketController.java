package com.sorahjy.buytickets.controller;


import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.service.TicketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/ticket")
public class SellerTicketController {

    @Autowired
    private TicketInfoService ticketInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        PageRequest request = new PageRequest(page - 1, size);
        Page<TicketInfo> ticketInfoPage = ticketInfoService.findAll(request);
        map.put("ticketInfoPage", ticketInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("ticket/list", map);
    }

}
