package com.sorahjy.buytickets.enums;

public enum ResultEnums {

    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    SCHOOL(100,"上学");
    ;

    private Integer code;
    private String msg;

    ResultEnums(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
