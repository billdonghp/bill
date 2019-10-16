package com.ait.api.resultApi.jpush.common;


import com.ait.api.resultApi.jpush.param.AppPushParam;
import org.json.JSONException;

public interface IAppPush {
	
	
	/**
	 * app 推送服务
	 * @return
	 */
	public boolean push(AppPushParam appPushParam) throws Exception;
}
