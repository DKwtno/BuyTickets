package com.sorahjy.buytickets.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TicketInfoVO {

    private String ticketId;
    private String ticketDepart;
    private BigDecimal ticketPrice;
    private Integer ticketStock;
    private String ticketArrive;
    private Date takeOff;

}
