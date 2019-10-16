package com.ait.api.resultApi;

import com.ait.api.dao.ApiDAO;
import com.ait.api.util.AES;
import com.ait.sqlmap.util.SimpleMap;
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
public class DooPushAPI {


    public static String APPLY_TYPE_LEAVE = "LEAVE";
    public static String APPLY_TYPE_OT = "OT";
    public static String APPLY_TYPE_AR = "AR";
    public static String APPLY_TYPE_PRESENT = "PRESENT";
    public static String APPLY_TYPE_EXPRESS = "EXPRESS";
    public static String APPLY_TYPE_SEAL = "SEAL";
    public static String APPLY_TYPE_TEMP_CARD = "TEMP_CARD";
    public static String APPLY_TYPE_VISITER = "VISITER";
    public static String APPLY_TYPE_VISA = "VISA";
    public static String APPLY_TYPE_EVS = "EVS";
    public static String APPLY_TYPE_EVS_RESULT = "EVS_RESULT";
    //AppKey(测试)
    //private static String AppKey = "903750F1B8000F83E053AB80280AA264";
    //请求url(测试)
    //private static String ReqURL = "https://doomobile.doosaninfracore.cn:5886/elight/ThirdPartInterface/AddELightNotice";

    //AppKey(正式)
    private static String AppKey = "919BA6072DD136BCE054AC162D91D772";
    //请求url(正式)
    private static String ReqURL = "https://mobile.doosaninfracore.cn:5886/elight/ThirdPartInterface/AddELightNotice";

    //根据推送人信息 推送
    public static void insertNotice(String applyType, String applyNo, String personId) {
        DooPushAPI.insertNotice(applyType, applyNo, "PERSON_ID",personId);
    }

    //申请时推送，推送第一级审批人
    public static void insertNotice(String applyType, String applyNo) {
        DooPushAPI.insertNotice(applyType, applyNo, 1);
    }

    //根据审批等级推送
    public static void insertNotice(String applyType, String applyNo, int affirmLevel) {
        DooPushAPI.insertNotice(applyType, applyNo, "AFFIRM_LEVEL",affirmLevel + "");
    }

    //推送最终处理方法
    private static void insertNotice(String applyType, String applyNo, String type, String value) {
        ApiDAO apiDAO = new ApiDAO();
        SimpleMap map = new SimpleMap();
        map.put("APPLY_TYPE", applyType);
        map.put("APPLY_NO", applyNo);

        List list = null;
        //获取接收人
        if ("AFFIRM_LEVEL".equals(type)) {
            map.put("AFFIRM_LEVEL", value);
            list = apiDAO.getInfoList(map, "getPushInfoByLevel");
        }else{
            map.put("SEND_ID", value);
            list = apiDAO.getInfoList(map, "getPushInfoByPersonID");
        }
        System.out.println(new Gson().toJson(map));
        if (list != null && list.size() > 0) {
            SimpleMap applyMap = (SimpleMap) list.get(0);
            String resultJson = "";//DooPushAPI.send(applyMap.getString("TITLE"),applyMap.getString("CONTENT"),applyMap.getString("SEND_ID"));
            if ("error".equals(resultJson)) {
                applyMap.put("ACTIVITY", "2");
            }else{
                applyMap.put("ACTIVITY", "1");
            }
            applyMap.put("MSG", resultJson);
            applyMap.put("APPLY_TYPE", applyType);
            applyMap.put("APPLY_NO", applyNo);
            apiDAO.insertNotice(applyMap);
        }
    }

    private static String send(String title, String content, String username) {
        try {
            Map json = new HashMap();

            //标题、内容
            json.put("TMI_TITLE", title);
            json.put("TMI_CONTENT", content);

            //01：打开app。02：打开新的地址 param:自定义参数
            Map parmMap = new HashMap();
            parmMap.put("type", "01");
            parmMap.put("param", "");
            json.put("TMI_PARM", parmMap);

            //ios 包名
            json.put("IOS_TAI_CODE", "com.xljs.pmapp");
            //android 包名
            json.put("ANDROID_TAI_CODE", "com.xljs.pmapp");
            //H5应用标识
            json.put("TAI_CODE", "");
            //应用类型  0：IOS 1：ANDROID 2：ALL
            json.put("TAI_TYPE", "1");
            //消息接收对象，逗号隔开，最多不能超过100个
            json.put("MSG_OBJS", username);
            json.put("MSG_OBJS_TYPE", "2");
            json.put("IS_NEED_PUSH", "1");
            String data = new Gson().toJson(json);
            System.out.println(data);
            String jsonStr = AES.encryptAES(data);
            System.out.println(jsonStr);

            /*Map paramMap = new HashMap();
            paramMap.put("AesParams", jsonStr);
            paramMap.put("Appkey", AppKey);*/

            String pushParmJsonStr = "{\"Appkey\":\"" + AppKey + "\"," +
                    "\"AesParams\":\"" + jsonStr + "\"}";

            String resultJson = DooPushAPI.post(pushParmJsonStr);

            return resultJson;
        } catch (Exception e) {
            Logger.getLogger(JPushAPI.class).error(e);
            Logger.getLogger(JPushAPI.class).error("别名[" + username + "]未注册");
            return "error";
        }
    }

    private static String post(String pushParmJsonStr) throws Exception {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder returnJson = new StringBuilder();
        try {

            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");//第一个参数为 返回实现指定安全套接字协议的SSLContext对象。第二个为提供者
            TrustManager[] tm = {new MyX509TrustManager()};
            sslContext.init(null, tm, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL realUrl = new URL(ReqURL);
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();

            conn.setSSLSocketFactory(ssf);
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
            System.out.println(pushParmJsonStr);
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
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }

        return returnJson.toString();
    }

    public static void main(String args[]) {
        try {
            System.out.println(DooPushAPI.send("人事审批测试", "有需要你审批的信息，请前往人事系统审批。", "20120262,ic1000412"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
