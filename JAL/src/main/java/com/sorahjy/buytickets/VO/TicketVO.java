package com.sorahjy.buytickets.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TicketVO implements Serializable {


    private static final long serialVersionUID = 5733334603690860962L;

    @JsonProperty("airlineName")
    private String airlineName;

    private Integer airlineType;


    @JsonProperty("tickets")
    private List<TicketInfoVO> ticketInfoVOList;



}
