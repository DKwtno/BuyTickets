package com.sorahjy.buytickets.utils;

import com.sorahjy.buytickets.enums.CodeEnum;

public class EnumUtil {
    public  static <T extends CodeEnum<Integer>> T getByCode(Integer code,Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
