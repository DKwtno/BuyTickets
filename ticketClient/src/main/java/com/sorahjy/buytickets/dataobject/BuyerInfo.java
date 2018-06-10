package com.sorahjy.buytickets.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class BuyerInfo {
    @Id
    @GeneratedValue
    private Integer buyerId;
    private String buyerName;
    private String password;
    private String buyerOpenid;
}
