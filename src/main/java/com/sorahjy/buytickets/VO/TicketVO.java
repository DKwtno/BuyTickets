package com.sorahjy.buytickets.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TicketVO {

    @JsonProperty("airlineName")
    private String airlineName;

    private String airlineType;


    @JsonProperty("tickets")
    private List<TicketInfoVO> ticketInfoVOList;



}
