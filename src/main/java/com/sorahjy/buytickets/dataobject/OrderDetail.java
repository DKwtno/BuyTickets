package com.sorahjy.buytickets.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String ticketId;

    private String ticketDepart;

    private String ticketArrive;

    private BigDecimal ticketPrice;

    private Integer ticketQuantity;

    private Date createTime;

    private Date UpdateTime;

}
