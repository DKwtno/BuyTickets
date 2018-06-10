package com.sorahjy.buytickets.VO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TicketInfoVO implements Serializable {


    private static final long serialVersionUID = -1926445534946963325L;

    private String ticketId;
    private String ticketDepart;
    private BigDecimal ticketPrice;
    private Integer ticketStock;
    private String ticketArrive;
    private Date takeOff;

}
