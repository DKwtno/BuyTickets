package com.sorahjy.buytickets.controller;

import com.sorahjy.buytickets.dataobject.AirlineInfo;
import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.form.TicketForm;
import com.sorahjy.buytickets.service.AirlineInfoService;
import com.sorahjy.buytickets.service.TicketInfoService;
import com.sorahjy.buytickets.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/ticket")
@Slf4j
public class SellerTicketController {

    @Autowired
    private TicketInfoService ticketInfoService;

    @Autowired
    private AirlineInfoService airlineInfoService;

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

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "ticketId",required = false
    )String ticketId,Map<String ,Object> map) {
        if (!StringUtils.isEmpty(ticketId)) {
            TicketInfo ticketInfo=ticketInfoService.findOne(ticketId);
            map.put("ticketInfo",ticketInfo);
        }

        //查询所有类目
        List<AirlineInfo> airlineInfoList=airlineInfoService.findAll();
        map.put("airlineInfoList", airlineInfoList);

        return new ModelAndView("ticket/index", map);
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam(value = "ticketId", required = false
    ) String ticketId, Map<String, Object> map) {

        ticketId=KeyUtil.genUniqueKey();
        TicketInfo ticketInfo=new TicketInfo();

        ticketInfo.setTicketId(ticketId);
        map.put("ticketInfo",ticketInfo);

        //查询所有类目
        List<AirlineInfo> airlineInfoList = airlineInfoService.findAll();
        map.put("airlineInfoList", airlineInfoList);

        return new ModelAndView("ticket/add", map);
    }



    @PostMapping("/save")
    public ModelAndView save(@Valid TicketForm form, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/tickets/seller/ticket/index");
        }
        log.error(form.toString());

        TicketInfo ticketInfo = ticketInfoService.findOne(form.getTicketId());
        LocalDateTime localDateTime = ticketInfo.getTakeOff();


        try {

            if (!StringUtils.isEmpty(form.getTicketId())) {
                ticketInfo = ticketInfoService.findOne(form.getTicketId());
            }else {
                form.setTicketId(KeyUtil.genUniqueKey());
            }

            BeanUtils.copyProperties(form, ticketInfo);

            String time = form.getTakeOff();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime localDateTime2 = LocalDateTime.parse(time + ":00", df);
            ticketInfo.setTakeOff(localDateTime2);
            ticketInfoService.save(ticketInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url","/tickets/seller/ticket/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/tickets/seller/ticket/list");
        return new ModelAndView("common/success", map);

    }

    @PostMapping("/newer")
    public ModelAndView newer (@Valid TicketForm form, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/tickets/seller/ticket/index");
        }
        log.error(form.toString());

        TicketInfo ticketInfo = new TicketInfo();
        String time=form.getTakeOff();

        try {


            BeanUtils.copyProperties(form, ticketInfo);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time+":00", df);
            ticketInfo.setTakeOff(localDateTime);
            ticketInfoService.save(ticketInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/tickets/seller/ticket/add");
            return new ModelAndView("common/error", map);

        }
        map.put("url", "/tickets/seller/ticket/list");
        return new ModelAndView("common/success", map);

    }
    @GetMapping("/del")
    public ModelAndView del(@RequestParam(value = "ticketId", required = true)String ticketId, Map<String, Object> map){
        ticketInfoService.deleteTicket(ticketId);
        map.put("url", "/tickets/seller/ticket/list");
        return new ModelAndView("common/success", map);
    }

}
