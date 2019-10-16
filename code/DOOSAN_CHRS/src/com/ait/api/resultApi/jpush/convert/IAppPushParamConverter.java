package com.ait.api.resultApi.jpush.convert;

import com.ait.api.resultApi.jpush.param.AppPushParam;
import org.json.JSONException;

public interface IAppPushParamConverter {
	public String convert(AppPushParam appPushParam) throws JSONException;
}
