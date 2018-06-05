package com.sorahjy.buytickets.enums;

import lombok.Getter;

@Getter
public enum TicketStatusEnum {
    UP(0,"已上架"),
    DOWN(1,"已下架")
    ;

    private Integer code;
    private String message;

    TicketStatusEnum(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }
}
