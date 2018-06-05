package com.sorahjy.buytickets.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class AirlineInfo {

    @Id
    @GeneratedValue
    private Integer airlineId;
    private String airlineName;
    private Integer airlineType;
    private Date createTime;
    private Date updateTime;

    public AirlineInfo() {
    }

    public AirlineInfo(final String airlineName, final Integer airlineType) {
        this.airlineName = airlineName;
        this.airlineType = airlineType;
    }
}
