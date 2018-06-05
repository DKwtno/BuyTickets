package com.sorahjy.buytickets.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
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
    private Date takeOff;

    private Date createTime;

    private Date updateTime;
}
