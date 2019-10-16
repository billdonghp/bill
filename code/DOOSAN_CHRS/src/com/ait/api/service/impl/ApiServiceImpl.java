package com.ait.api.service.impl;

import DSGAuthPkg.Auth;
import DSGAuthPkg.UserNotFoundException;
import com.ait.api.dao.ApiDAO;
import com.ait.api.service.ApiService;
import com.ait.api.util.AES;
import com.ait.ess.dao.EssArDAO;
import com.ait.ev.business.EvaluateApplyServices;
import com.ait.evs.business.EvsServices;
import com.ait.ga.business.*;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.StringUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApiServiceImpl
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/16 13:05
 * @Version 1.0.0
 **/
public class ApiServiceImpl implements ApiService {

    //每页数目
    int pageSize = 6;
    //检索条件
    String key;
    //查询区分  1：待审批  2：已审批
    int qryType;
    int currentPage;
    private String content;

    private ApiDAO apiDAO;

    public ApiServiceImpl(){
        apiDAO = new ApiDAO();
    }

    @Override
    public Map affirmInfoList(HttpServletRequest request) {
        Map paramMap = this.getData(request);
        String applyNo = StringUtil.checkNull(paramMap.get("applyNo"));
        content = StringUtil.checkNull(paramMap.get("content"));
        if (applyNo == null || "".equals(applyNo)) {
            qryType = Integer.parseInt(StringUtil.checkNull(paramMap.get("qryType"), "2"));
            currentPage = Integer.parseInt(StringUtil.checkNull(paramMap.get("currentPage"), "0"));
            //如果查询待审批，则查询所有
            if (qryType == 2) {
                pageSize = 20;
            }
            paramMap.put("qryType", qryType);
        }
        List list = apiDAO.getAffirmList(paramMap, currentPage, pageSize, content);
        if (content != null && !"".equals(applyNo) && list != null && list.size() > 0) {
            SimpleMap dataMap = (SimpleMap)list.get(0);
            String applyType = StringUtil.checkNull(paramMap.get("applyType"));
            if ("OtApply".equals(applyType)) {
                apiDAO.getPersonOtTime(dataMap);
            } else if ("ev".equals(applyType)) {
                EvaluateApplyServices evServices = new EvaluateApplyServices();
                List itemList = evServices.getEvaluateItem(dataMap.getString("EVALUATE_MONTH"),dataMap.getString("DEPTID"),dataMap.getString("POST_CODE"),dataMap.getString("PERSON_ID"),2);
                dataMap.put("itemList", itemList);
            }
            dataMap.put("affirmList",getAffirmorInfo(applyNo, applyType) );
        }
        Map map = new HashMap();
        map.put("list", list);
        return map;
    }

    public Map applyInfoList(HttpServletRequest request) {
        Map paramMap = this.getData(request);
        String applyNo = StringUtil.checkNull(paramMap.get("applyNo"));
        content = StringUtil.checkNull(paramMap.get("content"));

        List list = apiDAO.getAffirmList(paramMap, currentPage, pageSize, content);
        if (content != null && !"".equals(applyNo) && list != null && list.size() == 1) {
            SimpleMap dataMap = (SimpleMap)list.get(0);
            String applyType = StringUtil.checkNull(paramMap.get("applyType"));
            if ("OtApply".equals(applyType)) {
                apiDAO.getPersonOtTime(dataMap);
            }
            dataMap.put("affirmList",getAffirmorInfo(applyNo, StringUtil.checkNull(paramMap.get("applyType"))) );
        }
        Map map = new HashMap();
        map.put("list", list);
        return map;
    }

    public Map getCodeList(HttpServletRequest request) {
        Map paramMap = this.getData(request);
        content = StringUtil.checkNull(paramMap.get("content"));
        List list = apiDAO.getInfoList(paramMap,  content);

        Map map = new HashMap();
        map.put("list", list);
        return map;
    }

    @Override
    public Map paInfo(HttpServletRequest request) {
        return null;
    }

    public String login(HttpServletRequest request){
        Map resultMap = new HashMap();
        Map map = this.getData(request);
        //暂时注释掉，上线后需要ad认证判断
        String isAD = apiDAO.isAD(map);
        if (isAD != null && !"".equals(isAD)) {//非AD
            List list = apiDAO.loginForWorker(map);
            if (list != null && list.size() > 0) {
                Map userMap = (Map) list.get(0);
                return StringUtil.checkNull(userMap.get("EMPID"));
            }
        }else{
            String test = StringUtil.checkNull(map.get("test"));
            if ("test".equals(test)) {
                List list = apiDAO.login(map);
                if (list != null && list.size() > 0) {
                    Map userMap = (Map) list.get(0);
                    return StringUtil.checkNull(userMap.get("EMPID"));
                }
            }else{
                String username = StringUtil.checkNull(map.get("username"));
                String password = StringUtil.checkNull(map.get("password"));
                Auth oAuth = new Auth("authsj.corp.doosan.com", "dsg\\"+ username, password);
                Boolean bRet = null;
                try {
                    bRet = oAuth.validateUser(username, password);
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
                if (bRet) {
                    List list = apiDAO.loginForAd(map);
                    if (list != null && list.size() > 0) {
                        Map userMap = (Map) list.get(0);
                        return StringUtil.checkNull(userMap.get("EMPID"));
                    }
                }
            }
        }
        return null;
    }

    public List getLoginInfo(){
        return apiDAO.getLoginInfo();
    }

    public int modifyPassword(HttpServletRequest request){
        Map map = this.getData(request);
        List list = apiDAO.checkPassword(map);
        if (list == null || list.size() == 0) {
            return 2;
        } else {
            return apiDAO.modifyPassword(map);
        }
    }

    public int resetPassword(){
        return apiDAO.resetPassword();
    }

    @Override
    public List getInfoList(Map map,String target) {
        return this.apiDAO.getInfoList(map,target);
    }

    @Override
    public Map getCount(Map map, String target) {
        return this.apiDAO.getCount(map,target);
    }

    protected Map getData(HttpServletRequest request) {
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            Map map = new Gson().fromJson(reader, SimpleMap.class);
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

    private List getAffirmorInfo(String applyNo,String applyType) {
        List list = null;
        //加班&休假
        if ("LeaveApply".equals(applyType) || "OtApply".equals(applyType)) {
            EssArDAO essArDAO = new EssArDAO();
            list = essArDAO.getAffirmorList(Integer.parseInt(applyNo), applyType);
        } else if ("arModify".equals(applyType)) {
            EssArDAO essArDAO = new EssArDAO();
            list = essArDAO.getArModifyAffirmorList(Integer.parseInt(applyNo));
        } else if ("present".equals(applyType)) {
            GaServices services = new GaServices();
            SimpleMap parameterObject = new SimpleMap();
            parameterObject.set("applyNo", applyNo);
            list = services.retrievePresentAffirmList(parameterObject);
        } else if ("express".equals(applyType)) {
            ExpressApplyAndAffirmServices eaaServices = new ExpressApplyAndAffirmServices();
            SimpleMap parameterObject = new SimpleMap();
            parameterObject.set("applyno", applyNo);
            list = eaaServices.getExpressAffirmorList(parameterObject);
        } else if ("seal".equals(applyType)) {
            SealMangerSerivces smServices = new SealMangerSerivces();
            SimpleMap parameterObject = new SimpleMap();
            parameterObject.set("applyno", applyNo);
            list = smServices.getSealAffirmAndInformationAffirmorList(parameterObject);
        } else if ("tempCard".equals(applyType)) {
            EatingCardServices eatingCardes = new EatingCardServices();
            SimpleMap parameterObject = new SimpleMap();
            parameterObject.set("applyNo", applyNo);
            list=eatingCardes.getTempCardAffirmorList(parameterObject);
        } else if ("visiter".equals(applyType)) {
            VisiterApplicationsServices visiterApplicationsServices = new VisiterApplicationsServices();
            SimpleMap parameterObject = new SimpleMap();
            parameterObject.set("applyId", applyNo);
            list = visiterApplicationsServices.allVisiterApproval(parameterObject);
        } else if ("visa".equals(applyType)) {
            VisaMangerSerivces smServices = new VisaMangerSerivces();
            SimpleMap parameterObject = new SimpleMap();
            parameterObject.set("applyno", applyNo);
            list = smServices.getSealAffirmAndInformationAffirmorList(parameterObject);
        } else if ("ev".equals(applyType)) {
            EvaluateApplyServices evServices = new EvaluateApplyServices();
            list = evServices.getAffirmorList(Integer.parseInt(applyNo));
        } else if ("evs".equals(applyType)) {
            list = EvsServices.getInstance().getZyzgdjModifyAffirmorList(Integer.parseInt(applyNo));
        }
        return list;
    }
}
