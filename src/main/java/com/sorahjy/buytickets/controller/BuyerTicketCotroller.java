package com.sorahjy.buytickets.controller;

import com.sorahjy.buytickets.VO.ResultVO;
import com.sorahjy.buytickets.VO.TicketInfoVO;
import com.sorahjy.buytickets.VO.TicketVO;
import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.service.AirlineInfoService;
import com.sorahjy.buytickets.service.TicketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/ticket")
public class BuyerTicketCotroller {

    @Autowired
    private TicketInfoService ticketInfoService;

    @Autowired
    private AirlineInfoService airlineInfoService;

    @GetMapping("/list")
    public ResultVO list() {

        // 查询所有上架的商品
        List<TicketInfo> ticketInfoList=ticketInfoService.findUpAll();

        // 查询航空公司type
        //jdk7
        List<Integer> ticketTypeList=new ArrayList<>();
        for (TicketInfo ticketInfo : ticketInfoList) {
            ticketTypeList.add(ticketInfo.getAirlineType());
        }
        //jdk8



        // 数据拼装

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("OK!");
        TicketVO ticketVO = new TicketVO();
        TicketInfoVO ticketInfoVO = new TicketInfoVO();
        ticketVO.setTicketInfoVOList(Arrays.asList(ticketInfoVO));
        resultVO.setData(Arrays.asList(ticketVO));
        return resultVO;
    }


    @GetMapping("/listTest")
    public ResultVO listTest(){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("OK!");
        TicketVO ticketVO=new TicketVO();
        TicketInfoVO ticketInfoVO=new TicketInfoVO();
        ticketVO.setTicketInfoVOList(Arrays.asList(ticketInfoVO));
        resultVO.setData(Arrays.asList(ticketVO));
        return resultVO;
    }


}
