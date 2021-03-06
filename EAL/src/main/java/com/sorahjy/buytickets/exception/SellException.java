package com.sorahjy.buytickets.exception;

import com.sorahjy.buytickets.enums.ResultEnum;

public class SellException extends RuntimeException {

    private Integer code;

    public SellException (ResultEnum resultEnum) {
        super(resultEnum.getMesssage());
        this.code=resultEnum.getCode();
    }

    public SellException(final Integer code, final String defaultMessage) {

        super(defaultMessage);
        this.code=code;

    }
}
