package com.ait.api.cmd;

import com.ait.api.util.AES;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.StringUtil;
import com.ait.web.Command;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName AbstractCmd
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/15 9:49
 * @Version 1.0.0
 **/
public abstract class AbstractCmd implements Command{

    protected String getAdmin(HttpServletRequest request) {
        String empid = request.getParameter("empid");

        return empid;
    }
    protected Map getData(HttpServletRequest request) {
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            Map map = new Gson().fromJson(reader, SimpleMap.class);
            System.out.println(map.toString());
            map.put("empid", AES.Decrypt(StringUtil.checkNull(map.get("username"))));
            map.put("username", AES.Decrypt(StringUtil.checkNull(map.get("username"))));
            reader.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
