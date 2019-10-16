package com.ait.mobile.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @ClassName JasyptUtil
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/6/28 13:02
 * @Version 1.0.0
 **/
public class JasyptUtil {

    private static String PASSWORD = "FHD@SAH%F#J&DK$S%AFJDS";

    public static String encrypt(String str){
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(PASSWORD);

        return encryptor.encrypt(str);
    }

    public static String decrypt(String str) throws Exception{
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(PASSWORD);

        return encryptor.decrypt(str);
    }
}
