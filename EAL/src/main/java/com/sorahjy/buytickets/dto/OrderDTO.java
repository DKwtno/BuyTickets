package com.sorahjy.buytickets.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore //对象转成Json就会忽略
    public String getOrderStatusEnum(){
        //TODO此处有bug
        // return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
        if(orderStatus==0) return "未完成";
        else if(orderStatus==1) return "已完成";
        else return "已取消";
    }

}
