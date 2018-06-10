package com.sorahjy.buytickets.enums;

public enum OrderStatusEnum implements CodeEnum<Integer> {
    NEW(0,"未完成"),
    FINISHED(1,"已完成"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;
    public String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    OrderStatusEnum(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

}
