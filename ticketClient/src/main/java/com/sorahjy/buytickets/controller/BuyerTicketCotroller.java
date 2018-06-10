package com.sorahjy.buytickets.controller;

import com.sorahjy.buytickets.VO.ResultVO;
import com.sorahjy.buytickets.VO.TicketInfoVO;
import com.sorahjy.buytickets.VO.TicketVO;
import com.sorahjy.buytickets.dataobject.AirlineInfo;
import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.service.AirlineInfoService;
import com.sorahjy.buytickets.service.TicketInfoService;
import com.sorahjy.buytickets.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/ticket")
public class BuyerTicketCotroller {

    @Autowired
    private TicketInfoService ticketInfoService;

    @Autowired
    private AirlineInfoService airlineInfoService;

    @GetMapping("/list")
    @CachePut(cacheNames = "buyerTikectlist", key = "345")
    public ResultVO list() {

        // 查询所有上架的商品
        List<TicketInfo> ticketInfoList=ticketInfoService.findUpAll();

        // 查询航空公司type

        //jdk7
        // List<Integer> ticketTypeList=new ArrayList<>();
        // for (TicketInfo ticketInfo : ticketInfoList) {
        //     ticketTypeList.add(ticketInfo.getAirlineType());
        // }

        //jdk8
        List<Integer> ticketTypeList=ticketInfoList.stream()
            .map(e->e.getAirlineType())
            .collect(Collectors.toList());

        List<AirlineInfo> airlineInfoList=airlineInfoService.findByAirlineTypeIn(ticketTypeList);

        // 数据拼装
        List<TicketVO> ticketVOList=new ArrayList<>();
        for (AirlineInfo airlineInfo : airlineInfoList) {
            TicketVO ticketVO=new TicketVO();
            ticketVO.setAirlineType(airlineInfo.getAirlineType());
            ticketVO.setAirlineName(airlineInfo.getAirlineName());

            List<TicketInfoVO> ticketInfoVOList=new ArrayList<>();

            for (TicketInfo ticketInfo : ticketInfoList) {
                if(ticketInfo.getAirlineType().equals(airlineInfo.getAirlineType())){
                    TicketInfoVO ticketInfoVO=new TicketInfoVO();
                    BeanUtils.copyProperties(ticketInfo,ticketInfoVO);
                    ticketInfoVOList.add(ticketInfoVO);
                }
            }
            ticketVO.setTicketInfoVOList(ticketInfoVOList);
            ticketVOList.add(ticketVO);

        }


        return ResultVOUtil.success(ticketVOList);
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
