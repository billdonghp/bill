package com.ait.mobile.utils;

import sun.misc.BASE64Decoder;

import java.util.regex.Pattern;

/**
 * @ClassName IsBase64
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/8/27 20:13
 * @Version 1.0.0
 **/
public class IsBase64 {
    public static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }


    public static void main(String args[]) {
        try {
            String DATA1 = "aWMwNzMyMzI4";
            String DATA2 = "NjU0MzIx";
            // BASE64加密
            /*BASE64Encoder encoder = new BASE64Encoder();
            String data = encoder.encode(DATA.getBytes());
            System.out.println("BASE64加密：" + data);*/

            // BASE64解密
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(DATA1);
            System.out.println("BASE64解密：" + new String(bytes));

            bytes = decoder.decodeBuffer(DATA2);
            System.out.println("BASE64解密：" + new String(bytes));
            // 结果
            // BASE64加密：Y29tLmJhc2U2NC5kZW1v
            // BASE64解密：com.base64.demo
        } catch (Exception e) {
            System.out.println("BASE64加解密异常");
            e.printStackTrace();
        }
    }
}
