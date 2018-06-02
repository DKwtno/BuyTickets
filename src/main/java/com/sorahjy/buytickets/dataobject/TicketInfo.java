package com.sorahjy.buytickets.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class TicketInfo {
    @Id
    private String ticketId;
    private String ticketDepart;
    private BigDecimal ticketPrice;
    private  Integer ticketStock;
    private  String ticketArrive;

    //airline info 中的 airline Type
    private Integer airlineType;
    // private Integer ticketStatus;



}
