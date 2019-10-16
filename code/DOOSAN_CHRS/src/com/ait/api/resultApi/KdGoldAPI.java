package com.ait.api.resultApi;

import com.ait.api.dao.ApiDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName KdGoldAPI
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/11 17:12
 * @Version 1.0.0
 **/
public class KdGoldAPI {
    //电商ID
    private static String EBusinessID="1557731";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private static String AppKey="bf14e4e0-5ed5-409e-beb6-3df561b70885";
    //请求url, 正式环境地址：http://api.kdniao.com/api/Eorderservice    测试环境地址： http://testapi.kdniao.com:8081/api/Eorderservice
    private static String ReqURL = "http://api.kdniao.com/api/Eorderservice";
    private static String ReqTrackURL = "http://api.kdniao.com/api/Eorderservice";

    /**
     * Json方式 电子面单
     * @throws Exception
     */
    public static String orderOnlineByJson(String applyNo) throws Exception{

        //获取快递信息
        SimpleMap batisMap = new SimpleMap();
        batisMap.put("APPLY_NO", applyNo);
        ApiDAO apiDAO = new ApiDAO();
        List resultList = apiDAO.getInfoList(batisMap, "getExpInfo");

        if (resultList != null && resultList.size() > 0) {

            SimpleMap map = (SimpleMap)resultList.get(0);
            SimpleMap paramMap = new SimpleMap();
            paramMap.put("OrderCode", map.getString("APPLY_NO"));
        /*paramMap.put("CustomerName", "90000004692720");
        paramMap.put("CustomerPwd", "90000004692720");*/
            paramMap.put("ShipperCode", map.getString("EXPRESSTYPE"));
            paramMap.put("PayType", "3");
            paramMap.put("ExpType", "1");

            SimpleMap senderMap = new SimpleMap();
            senderMap.put("Name", map.getString("SENDPERSON"));
            senderMap.put("Mobile", map.getString("MOBILE_S"));
            senderMap.put("ProvinceName", map.getString("CITYSENDTO_S"));
            senderMap.put("CityName", map.getString("CITYSENDTO"));
            senderMap.put("ExpAreaName", map.getString("CITYSENDTO_Q"));
            senderMap.put("Address", map.getString("CITYSENDTO_D"));
            senderMap.put("PostCode", map.getString("POST_CODE_S"));
            paramMap.put("Sender", senderMap);

            SimpleMap receiverMap = new SimpleMap();
            receiverMap.put("Company", map.getString("POSTADDRESS"));
            receiverMap.put("Name", map.getString("SENDTOPERSON"));
            receiverMap.put("Mobile", map.getString("MOBILE_A"));
            receiverMap.put("ProvinceName", map.getString("CITYARRIVE_S"));
            receiverMap.put("CityName", map.getString("CITYARRIVE"));
            receiverMap.put("ExpAreaName", map.getString("CITYARRIVE_Q"));
            receiverMap.put("Address", map.getString("CITYARRIVE_D"));
            receiverMap.put("PostCode", map.getString("POST_CODE_A"));
            paramMap.put("Receiver", receiverMap);

            SimpleMap commodityMap = new SimpleMap();
            commodityMap.put("GoodsName", map.getString("EXP_TYPE"));

            List goodsList = new ArrayList();
            goodsList.add(commodityMap);

            paramMap.put("Commodity", goodsList);
            paramMap.put("IsReturnPrintTemplate", "1");

            String requestData= new Gson().toJson(paramMap);

            System.out.println(requestData);
            Map<String, String> params = new HashMap<String, String>();
            params.put("RequestData", urlEncoder(requestData, "UTF-8"));
            params.put("EBusinessID", EBusinessID);
            params.put("RequestType", "1007");
            String dataSign=encrypt(requestData, AppKey, "UTF-8");
            params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
            params.put("DataType", "2");

            String result=sendPost(ReqURL, params);

            batisMap.put("MSG", result);
            batisMap.put("POSTNUMBER", "error");
            SimpleMap upMap = new Gson().fromJson(result, SimpleMap.class);
            if (upMap != null && "100".equals(upMap.getString("ResultCode"))) {
                LinkedTreeMap orderMap = (LinkedTreeMap) upMap.get("Order");
                if (orderMap != null && !"".equals(orderMap.get("LogisticCode"))) {
                    batisMap.put("POSTNUMBER", StringUtil.checkNull(orderMap.get("LogisticCode")));
                }
            }
            //根据公司业务处理返回的信息......
            apiDAO.updateExp(batisMap);
            return result;
        }

        return "error";

        /*map.put("APPLY_NO", "123456");
        map.put("CITYSENDTO_S", "河南省");
        map.put("CITYSENDTO", "南阳市");
        map.put("CITYSENDTO_Q", "新野县");
        map.put("CITYSENDTO_D", "汉城广场");
        map.put("MOBILE_S", "15011000261");
        map.put("SENDPERSON", "魏某某");

        map.put("CITYARRIVE_S", "广东省");
        map.put("CITYARRIVE", "深圳市");
        map.put("CITYARRIVE_Q", "什么区");
        map.put("CITYARRIVE_D", "世界之窗");
        map.put("MOBILE_A", "15011000262");
        map.put("SENDTOPERSON", "周某某");
        map.put("POSTADDRESS", "ait");
        map.put("EXP_TYPE", "文件");*/

        /*"{'OrderCode': '123456'," +
                //电子面单客户号
                "'CustomerName':'90000004692720'," +
                "'CustomerPwd':''," +
                //快递公司编码
                "'ShipperCode':'EMS'," +
                //运费支付方式： 1-现付，2-到付，3-月结，4- 第三方付(仅 SF、KYSY 支持)
                "'PayType':3," +
                //标准快递
                "'ExpType':1," +
                //发件人信息
                "'Sender':" +
                "{" +
                "'Company':'LV','Name':'Taylor','Mobile':'15018442396','ProvinceName':'上海','CityName':'上海','ExpAreaName':'青浦区','Address':'明珠路73号','PostCode',''}," +
                //收件人信息
                "'Receiver':" +
                "{" +
                "'Company':'GCCUI','Name':'Yann','Mobile':'15018442396','ProvinceName':'北京','CityName':'北京','ExpAreaName':'朝阳区','Address':'三里屯街道雅秀大厦','PostCode',''}," +
                "'Commodity':" +
                "[{'GoodsName':'鞋子'}]," +
                //是否返回电子面单模板： 0-不需要，1-需要
                "'IsReturnPrintTemplate':1}";*/


    }

    /**
     * Json方式 电子面单
     * @throws Exception
     */
    public static String orderOnlineByJsonJson() throws Exception{
        String requestData= "{'OrderCode': '719231111'," +
                "'ShipperCode':'EMS'," +/*
                "'CustomerName':'90000004692720'," +
                "'CustomerPwd':'123456'," +*/
                "'PayType':1," +
                "'ExpType':1," +
                "'Cost':1.0," +
                "'OtherCost':1.0," +
                "'Sender':" +
                "{" +
                "'Company':'','Name':'孙莉莉','Mobile':'15376998848','ProvinceName':'山东省','CityName':'烟台市','ExpAreaName':'福山区','Address':'开发区五指山路28号','PostCode':'264006'}," +
                "'Receiver':" +
                "{" +
                "'Company':'SCANIA','Name':'Georgiana','Mobile':'13701671747','ProvinceName':'上海','CityName':'上海','ExpAreaName':'长宁','Address':'兴义路8号万都中心2701室','PostCode':'200336'}," +
                "'Commodity':" +
                "[{" +
                "'GoodsName':'文件'}]," +
                "'IsReturnPrintTemplate':1}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1007");
        String dataSign=encrypt(requestData, AppKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=sendPost(ReqURL, params);

        //根据公司业务处理返回的信息......
        System.out.println(result);

        return result;
    }
    /**
     * Json方式 查询订单物流轨迹
     * @throws Exception
     */
    public static String getOrderTracesByJson(String expType, String expNo) throws Exception{
        String requestData= "{'OrderCode':'','ShipperCode':'" + expType + "','LogisticCode':'" + expNo + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1002");
        String dataSign=encrypt(requestData, AppKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=sendPost(ReqTrackURL, params);

        //根据公司业务处理返回的信息......
        return result;
    }
    /**
     * MD5加密
     * @param str 内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * @param str 内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private static String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private static String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * @param content 内容
     * @param keyValue Appkey
     * @param charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    private static String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求的参数集合
     * @return 远程资源的响应结果
     */
    private static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if(param.length()>0){
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                    System.out.println(entry.getKey()+":"+entry.getValue());
                }
                System.out.println("param:"+param.toString());
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static void main(String args[]){
        try {
            SimpleMap map = new SimpleMap();
            //System.out.println(api.orderOnlineByJson("70899"));
            KdGoldAPI.orderOnlineByJsonJson();
//            System.out.print(KdGoldAPI.getOrderTracesByJson("1071687897480"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
