package com.ait.api.resultApi.jpush.common;


import com.ait.api.resultApi.jpush.param.AppPushParam;
import org.json.JSONException;

public class AppPushManger {
	
	private IAppPush appPush;
	
	public AppPushManger(IAppPush appPush) {
		this.appPush = appPush;
	}
	
	public boolean push(AppPushParam appPushParam) throws Exception {
		return appPush.push(appPushParam);
	}
}
