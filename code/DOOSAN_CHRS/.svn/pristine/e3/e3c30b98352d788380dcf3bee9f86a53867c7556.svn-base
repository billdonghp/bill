package com.ait.api.resultApi;

import com.ait.api.dao.ApiDAO;
import com.ait.api.util.AES;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.StringUtil;
import com.ait.utils.MyX509TrustManager;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DooPushAPI
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/8/19 14:24
 * @Version 1.0.0
 **/
public class SMSAPI {

    //AppKey
    private static String account="dh18195";
    private static String password="59d6718a3930070798523ff29d41d9c7";
    private static String sign="【斗山工程机械】";
    //请求url
    private static String ReqURL = "http://www.dh3t.com/json/sms/Submit";

    public static void send(String applyNo){
        try {
            Map paramMap = new SimpleMap();
            paramMap.put("APPLY_NO", applyNo);

            //获取需发送的信息
            ApiDAO apiDAO = new ApiDAO();
            List<SimpleMap> list = apiDAO.getInfoList(paramMap, "getCardInfo");

            //循环发送
            for (SimpleMap map : list) {

                Map json = new SimpleMap();
                //标题、内容
                json.put("account", account);
                json.put("password", password);
                json.put("msgid", "");
                json.put("phones", map.getString("DRIVER_PHONE"));
                //测试阶段 发送测试人手机号
                //json.put("phones", "15011000261,18500790272");

                json.put("content", "请您在" + map.getString("USE_START_DATE") + "前"+ map.getString("DISTINCTION")  + map.getString("LARDER_POST") + map.getString("LARDER") +
                                "等(" + map.getString("APPLY_USERSCOUNT") + ")人,路线:" + map.getString("DRIVE_WAY") +
                        ",备注:" + map.getString("NOTE") +
                        ".如有异议，请联系申请人" + map.getString("CHINESENAME") + map.getString("TELL_PHONE"));
                json.put("sign", sign);
                json.put("subcode", "");
                json.put("sendtime", map.getString("SEND_TIME"));

//              String resultJson = "{\"msgid\":\"fad2e69fc4c14a95aa849a586b8532cd\",\"result\":\"0\",\"desc\":\"提交成功\",\"failPhones\":\"\"}";
                String resultJson = SMSAPI.post(new Gson().toJson(json));

                System.out.println(resultJson);
                SimpleMap resultMap = new Gson().fromJson(resultJson, SimpleMap.class);

                json.put("APPLY_NO", applyNo);
                json.put("MSG", resultJson);
                if (resultMap != null && "0".equals(resultMap.getString("result"))) {
                    json.put("FLAG", 1);
                }else{
                    json.put("FLAG", 0);
                }
                apiDAO.insertSms(json);
                System.out.println(resultJson);
            }
        } catch (Exception e) {
            Logger.getLogger(JPushAPI.class).error(e);
        }
    }

    public static String post(String pushParmJsonStr) throws Exception {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder returnJson = new StringBuilder();
        try {

            URL realUrl = new URL(ReqURL);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性

            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(pushParmJsonStr);
            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                returnJson.append(line);
            }
        } catch (Exception e) {
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                throw ex;
            }
        }

        return returnJson.toString();
    }

    public static void main(String args[]){
        try {
            SMSAPI.send("22347");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
