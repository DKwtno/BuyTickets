package com.sorahjy.buytickets.enums;

public enum PayStatusEnum {

    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功"),
    ;

    PayStatusEnum(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

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

    private Integer code;

    private String message;

}
