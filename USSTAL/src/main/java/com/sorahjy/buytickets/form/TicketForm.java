package com.sorahjy.buytickets.form;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketForm {

    private String ticketId;

    private String ticketDepart;

    private BigDecimal ticketPrice;

    private Integer ticketStock;

    private String ticketArrive;

    //airline info 中的 airline Type
    private Integer airlineType;

    // private Integer ticketStatus;


    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh-hh-ss")
    // private LocalDateTime takeOff;
    private String takeOff;


    // public void setTakeOff(final String takeOff) {
    //     DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //     this.takeOff = LocalDateTime.parse(takeOff, df);
    // }
}
