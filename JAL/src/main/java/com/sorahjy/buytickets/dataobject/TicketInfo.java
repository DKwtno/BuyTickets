package com.sorahjy.buytickets.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm-ss")
    private LocalDateTime takeOff;

    private Date createTime;

    private Date updateTime;



    // public void setTakeOff(final String takeOff) {
    //     DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //     this.takeOff = LocalDateTime.parse(takeOff, df);
    // }



}
