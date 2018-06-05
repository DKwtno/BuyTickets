package com.sorahjy.buytickets.enums;

public enum ResultEnum {

    PRODUCT_NOT_EXIST(473,"商品不存在"),
    PRODUCT_STOCK_ERROR(470,"库存不足"),
    ORDER_NOT_EXIST(471,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(472,"订单详情存在"),
    UNKNOWN_ERROR(404,"其他错误"),
    PARAM_ERROR(403,"参数错误"),
    CART_EMPTY(401,"购物车空"),
    ORDER_OWNER_ERROR(402,"订单主任非法"),
    ;
    //TODO 补充并修改一下错误处理

    private Integer code;
    private String messsage;

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(final String messsage) {
        this.messsage = messsage;
    }

    ResultEnum(final Integer code, final String messsage) {

        this.code = code;
        this.messsage = messsage;
    }
}
