package com.ait.api.service;

import com.ait.sqlmap.util.SimpleMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApiService
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/15 18:40
 * @Version 1.0.0
 **/
public interface ApiService {

    public Map affirmInfoList(HttpServletRequest request);

    public Map applyInfoList(HttpServletRequest request);

    public Map getCodeList(HttpServletRequest request);

    public Map paInfo(HttpServletRequest request);

    public String login(HttpServletRequest request);

    public List getLoginInfo();

    public int modifyPassword(HttpServletRequest request);

    public int resetPassword();

    public List getInfoList(Map map,String target);

    public Map getCount(Map map, String target);
}
