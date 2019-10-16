package com.ait.api.resultApi.jpush.common;

import com.ait.api.resultApi.jpush.config.AppPushConfig;
import com.ait.api.resultApi.jpush.convert.IAppPushParamConverter;
import com.ait.api.resultApi.jpush.param.AppPushParam;
import org.apache.log4j.Logger;
import org.json.JSONException;

public abstract class AbstractAppPush implements IAppPush{
	private static final Logger log = Logger.getLogger(AbstractAppPush.class);
    protected IAppPushParamConverter appPushParamConverter;
    protected AppPushConfig appPushConfig;
    
	/**
	 * @param appPushParam
	 * @return
	 */
	public boolean push(AppPushParam appPushParam) throws Exception {
		
		String pushParmJsonStr = convertAppPushParam(appPushParam);
    	String authorization = getAuthorization(appPushConfig);
    	
    	String returnJson = post(appPushConfig,authorization,pushParmJsonStr);
    	if(returnJson!=null){
    		log.info("app push sucess:"+returnJson);
    		return true;
    	}
    	log.info("app push fail!");
    	return false;
	}
	
	
	/**
	 * 认证信息获取
	 * @param appPushConfig
	 * @return
	 */
	public abstract String getAuthorization(AppPushConfig appPushConfig);
	/**
	 *  参数转换
	 *  @param appPushParam
	 */
	public abstract String convertAppPushParam(AppPushParam appPushParam) throws JSONException;
	/**
	 *  调用推送第三放服务
	 * @param appPushParam
	 * @param authorization
	 * @param pushParmJsonStr
	 * @return
	 */
	public abstract String post(AppPushConfig appPushConfig, String authorization, String pushParmJsonStr) throws Exception;
}
