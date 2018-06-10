package com.sorahjy.buytickets.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message="买家姓名必填")
    private String buyerName;

    @NotEmpty(message="买家电话必填")
    private String buyerPhone;

    @NotEmpty(message = "openid必填")
    private String buyerOpenid;

    @NotEmpty(message = "购物车必填")
    private String items;

}
