package com.sorahjy.buytickets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sorahjy.buytickets.dataobject.OrderDetail;
import com.sorahjy.buytickets.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@JsonInclude(Include.NON_NULL) //把属性为null的字段去掉
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus ;

    private Integer payStatus ;

    @JsonSerialize(using=Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

}
