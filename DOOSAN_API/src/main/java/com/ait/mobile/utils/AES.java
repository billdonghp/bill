package com.ait.mobile.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AES
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/19 16:38
 * @Version 1.0.0
 **/
public class AES {

    private static String sKey = "jfk%sj&fj768nmj3";
    private static String pushKey = "6000000318145551";
    private static String pushVi = "0123456789abcdef";
    // 加密
    public static String Encrypt(String sSrc) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return Base64.encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static String encryptAES(String data ) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding"); // 参数分别代表 算法名称/加密模式/数据填充方式
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(pushKey.getBytes(), "AES");

            IvParameterSpec ivspec = new IvParameterSpec(pushVi.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return Base64.encode(encrypted);
            // 此处若为 JDK1.7 及以下
            //请将 base64 加密代码替代为:
            //new sun.misc.BASE64Encoder().encode(encrypted);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptAES(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding"); // 参数分别代表 算法名称/加密模式/数据填充方式
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = Base64.decode(data);
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(pushKey.getBytes(), "AES");

            IvParameterSpec ivspec = new IvParameterSpec(pushVi.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new String(encrypted,"utf-8");
            // 此处若为 JDK1.7 及以下
            //请将 base64 加密代码替代为:
            //new sun.misc.BASE64Encoder().encode(encrypted);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
