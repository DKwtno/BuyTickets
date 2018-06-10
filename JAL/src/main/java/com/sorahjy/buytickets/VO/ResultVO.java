package com.sorahjy.buytickets.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -452716734913495528L;

    private Integer code;
    private String msg;
    private T data;
}
