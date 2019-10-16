package com.ait.api.resultApi.jpush.impl;

import com.ait.api.resultApi.jpush.common.AbstractAppPush;
import com.ait.api.resultApi.jpush.config.AppPushConfig;
import com.ait.api.resultApi.jpush.config.impl.JiGuangConfig;
import com.ait.api.resultApi.jpush.convert.impl.JiGuangAppPushParamConverter;
import com.ait.api.resultApi.jpush.param.AppPushParam;
import com.ait.utils.MyX509TrustManager;
import org.json.JSONException;
import sun.misc.BASE64Encoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;
import java.security.SecureRandom;

public class JiGuangPushImpl  extends AbstractAppPush {

	public JiGuangPushImpl() {
		super.appPushParamConverter = new JiGuangAppPushParamConverter();
		super.appPushConfig = new JiGuangConfig();
	}
	
	@Override
	public String getAuthorization(AppPushConfig appPushConfig) {
		String appKey = appPushConfig.getAppKey();
		String masterSecret = appPushConfig.getMasterSecret();
		 String base64_auth_string = encryptBASE64(appKey + ":" + masterSecret);
	     String authorization = "Basic " + base64_auth_string;
	    return authorization;
	}

	@Override
	public String convertAppPushParam(AppPushParam appPushParam) throws JSONException {
		String pushParmJsonStr = appPushParamConverter.convert(appPushParam);
		return pushParmJsonStr;
	}

	@Override
	public String post(AppPushConfig appPushConfig, String authorization, String pushParmJsonStr) throws Exception {

		System.out.println(authorization);

		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder returnJson = new StringBuilder();
		try {

			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");//第一个参数为 返回实现指定安全套接字协议的SSLContext对象。第二个为提供者
			TrustManager[] tm = {new MyX509TrustManager()};
			sslContext.init(null, tm, new SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL realUrl = new URL(appPushConfig.getPushUrl());
			HttpsURLConnection conn =(HttpsURLConnection) realUrl.openConnection();

			conn.setSSLSocketFactory(ssf);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性

			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("Authorization", authorization.trim());
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
	
	/**
	 * BASE64加密工具s
	 * @param str
	 * @return
	 */
	 public static String encryptBASE64(String str) {
	     byte[] key = str.getBytes();
	   BASE64Encoder base64Encoder = new BASE64Encoder();
	   String strs = base64Encoder.encodeBuffer(key);
	     return strs;
	 }
}
