package com.ait.api.resultApi;

import com.ait.api.resultApi.jpush.common.AppPushManger;
import com.ait.api.resultApi.jpush.enumClass.PlatFormEnum;
import com.ait.api.resultApi.jpush.impl.JiGuangPushImpl;
import com.ait.api.resultApi.jpush.param.AppPushParam;
import org.apache.log4j.Logger;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JPushAPI
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/12 16:52
 * @Version 1.0.0
 **/
public class JPushAPI {
    public static void send(String title,String content,String username){
        AppPushParam appPushParam = new AppPushParam();
        //推送弹窗的信息内容
        appPushParam.setTitle(title);
        appPushParam.setMessage(content);
        appPushParam.setAlias(username);
        //推动的自定义信息
        Map<String, String> homeExtrasInfo = new HashMap<String,String>();
        appPushParam.setExtras(homeExtrasInfo);
        //设置推送的平台
        List<PlatFormEnum> platformList = new ArrayList<PlatFormEnum>();
        platformList.add(PlatFormEnum.ANDROID);
        //platformList.add(PlatFormEnum.IOS);
        appPushParam.setPlatform(platformList);
        //设置推送目标 如果不设置就是广播模式
    	/*Map<String, String[]> audience = new HashMap<String,String[]>();
    	audience.put("registration_id", new String[] {"140fe1da9efeac6fd85"});//指定设备的registration_id进行发送
    	appPushParam.setAudience(audience);*/
        //设置开发环境 极光推送只是对ios有效
        appPushParam.setPushEnvironment(false);
        //设置离线消息保留时长(秒)
        appPushParam.setTimeToLive(60);

        AppPushManger manger = new AppPushManger(new JiGuangPushImpl());
        try {
            manger.push(appPushParam);
        } catch (Exception e) {
            Logger.getLogger(JPushAPI.class).error(e);
            Logger.getLogger(JPushAPI.class).error("别名[" + username + "]未注册");
        }
    }
}
