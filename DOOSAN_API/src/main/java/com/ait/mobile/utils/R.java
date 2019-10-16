package com.ait.mobile.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2018/6/9 19:31
 */
public class R extends HashMap<String, Object> {

    public R() {
        put("resultCode", 200);
    }

    public static R error() {
        return error(300, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(300, msg);
    }

    public static R error(int resultCode, String msg) {
        R r = new R();
        r.put("resultCode", resultCode);
        r.put("msg", msg);
        return r;
    }

    /**
     * 喜庆专属错误返回
     *
     * @Author weizhengchen
     * @Email 1377252306@qq.com
     * @Date 2018/8/3 9:41
     * @Param []
     * @Return com.wei.hr.inf.utils.R
     */
    public static R death(){
        return error("您成功杀死一个程序员");
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.put("data",map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
