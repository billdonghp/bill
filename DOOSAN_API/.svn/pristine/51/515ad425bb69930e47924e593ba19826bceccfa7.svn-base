package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.model.AffirmQuery;
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
@Api(tags = "2.公章申请页面所需接口")
@RestController
@RequestMapping(value = "/mobile/api/seal")
public class ApplySealController extends BaseController {

    @PostMapping("/apply")
    @Authorization
    @ApiOperation(value = "信息申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R apply(@ApiIgnore @CurrentUser User user,@RequestBody @ApiParam(name="公章申请对象",value="传入json格式",required=true) Seal seal) {
        seal.setUsername(user.getUsername());
        seal.setApplerId(seal.getPersonId());
        System.out.println(seal);
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=applySeal" , seal,R.class);
    }

    @PostMapping("/getAffirmor")
    @Authorization
    @ApiOperation(value = "查询审批线（ affirmorDuty 不是这几个的'C_12005_93775','C_12005_43','C_12005_1330060'，可以删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "adminId", value = "登录者personID", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cpnyId", value = "登录者法人id", required = true , dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sealType", value = "印章类型", required = true , dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isLend", value = "是否外借，1是，0否", required = true , dataType = "string", paramType = "query")
    })
    public R getAffirmor(@ApiIgnore @CurrentUser User user, @RequestParam String adminId, @RequestParam String sealType, @RequestParam String isLend, @RequestParam String cpnyId) {
        Map map = new HashMap();
        map.put("content", "getAffirmor");
        map.put("applyType", "SealApply");
        map.put("adminId", adminId);
        map.put("cpnyId", cpnyId);
        map.put("sealType", sealType);
        map.put("isLend", isLend);
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=applySeal" , map,R.class);
    }


    @PostMapping("/getDocumentNo")
    @Authorization
    @ApiOperation(value = "查询公章编号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R getDocumentNo(@ApiIgnore @CurrentUser User user) {
        Map map = new HashMap();
        map.put("content", "getDocumentNo");
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=applySeal" , map,R.class);
    }
}
