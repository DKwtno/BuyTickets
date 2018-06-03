package com.sorahjy.buytickets.utils;

import com.sorahjy.buytickets.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setMsg("OK!");
        resultVO.setCode(200);
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
