package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.model.Express;
import com.ait.mobile.model.Seal;
import com.ait.mobile.model.User;
import com.ait.mobile.utils.R;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2018/7/11 12:09
 * @Version 1.0.0
 **/
@Api(tags = "2.快件申请页面所需接口")
@RestController
@RequestMapping(value = "/mobile/api/express")
public class ApplyExpressController extends BaseController {

    @PostMapping("/apply")
    @Authorization
    @ApiOperation(value = "信息申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R apply(@ApiIgnore @CurrentUser User user,@RequestBody @ApiParam(name="快件申请对象",value="传入json格式",required=true) Express express) {
        express.setUsername(user.getUsername());
        express.setApplerId(express.getPersonId());
        express.setAdminId(express.getPersonId());
        System.out.println(express);
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=applyExpress" , express,R.class);
    }

    @PostMapping("/getAffirmor")
    @Authorization
    @ApiOperation(value = "查询审批线（ affirmorDuty 不是这几个的'C_12005_93775','C_12005_43','C_12005_1330060'，可以删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "adminId", value = "登录者personID", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cpnyId", value = "登录者法人id", required = true , dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isKg", value = "0:小于1公斤 1：大于1公斤", required = true , dataType = "string", paramType = "query")
    })
    public R getAffirmor(@ApiIgnore @CurrentUser User user, @RequestParam String adminId, @RequestParam String cpnyId, @RequestParam(required = false) String isKg) {
        Map map = new HashMap();
        map.put("content", "getAffirmor");
        map.put("applyType", "ExpressApply");
        map.put("adminId", adminId);
        map.put("cpnyId", cpnyId);
        map.put("isKg", isKg == null ? "0" : isKg);
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=applyExpress" , map,R.class);
    }

    @PostMapping("/searchEmp")
    @Authorization
    @ApiOperation(value = "查询发件人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "adminId", value = "登录者personID", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cpnyId", value = "登录者法人id", required = true , dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "key", value = "查询key" , dataType = "string", paramType = "query")
    })
    public R searchEmp(@ApiIgnore @CurrentUser User user, @RequestParam String adminId, @RequestParam String cpnyId, @RequestParam String key) {
        Map map = new HashMap();
        map.put("key", key);
        map.put("adminId", adminId);
        map.put("cpnyId", cpnyId);
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=searchEmployeeCmd" , map,R.class);
    }


    @PostMapping("/orderTraces")
    @Authorization
    @ApiOperation(value = "查询物流轨迹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "expType", value = "快递类型", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "expNo", value = "快递编码", required = true, dataType = "string", paramType = "query")
    })
    public R orderTraces(@ApiIgnore @CurrentUser User user, @RequestParam String expType, @RequestParam String expNo) {
        Map map = new HashMap();
        map.put("expNo", expNo);
        map.put("expType", expType);
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=orderTraces" , map,R.class);
    }
}
