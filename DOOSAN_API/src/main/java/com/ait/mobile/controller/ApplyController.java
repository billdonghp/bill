package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.model.User;
import com.ait.mobile.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Author weizhengchen
 * @Email 1377252306@qq.comgetCodeInfo
 * @Date 2018/7/11 12:09
 * @Version 1.0.0
 **/
@Api(tags = "2.信息申请（代码信息查询）")
@RestController
@RequestMapping(value = "/mobile/api")
public class ApplyController extends BaseController {

    @PostMapping("/codeInfo/{content}")
    @Authorization
    @ApiOperation(value = "查询代码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "content", value = "代码类型：getCpnyList（所在法人列表），getCodeList（代码列表）", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "parentCode", value = "父级代码：LeaveTypeCode（休假列表），OTTypeCode(加班类型)，SealType（印章类型），EMP_DIFF（全部法人列表，公章申请使用），EXP_TYPE（快件类型）" , dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cpnyId", value = "查询代码的时候传cpnyId" , dataType = "string", paramType = "query")
    })
    public R codeInfo(@ApiIgnore @CurrentUser User user, @PathVariable String content, @RequestParam(required = false) String parentCode, @RequestParam(required = false) String cpnyId ) {
        Map map = new HashMap();
        map.put("content", content);
        map.put("parentCode", parentCode);
        map.put("username", user.getUsername());
        map.put("cpnyId", cpnyId);
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=getCodeInfo" , map,R.class);
    }
}
