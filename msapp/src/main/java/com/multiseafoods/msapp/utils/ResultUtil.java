package com.multiseafoods.msapp.utils;

import com.multiseafoods.msapp.entity.Result;

public class ResultUtil {

    public static Result ok(Object obj){
        Result result = new Result();
        result.setData(obj);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static  Result ok(){
        return ok(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(code);
        return result;

    }

}
