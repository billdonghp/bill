package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.authorization.manager.TokenManager;
import com.ait.mobile.authorization.model.TokenModel;
import com.ait.mobile.model.AffirmQuery;
import com.ait.mobile.model.User;
import com.ait.mobile.utils.Constants;
import com.ait.mobile.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @ClassName LoginController
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2018/7/11 12:09
 * @Version 1.0.0
 **/
@Api(tags = "4.审批信息查询")
@RestController
@RequestMapping(value = "/mobile/api")
public class AffirmInfoController extends BaseController {

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/affirmInfo")
    @Authorization
    @ApiOperation(value = "查询审批信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R affirmInfo(@ApiIgnore @CurrentUser User user,@RequestBody @ApiParam(name="查询条件对象",value="传入json格式",required=true) AffirmQuery affirmQuery) {
        affirmQuery.setUsername(user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=affirmInfo" , affirmQuery,R.class);
    }

    @PostMapping("/evsDeptInfo")
    @Authorization
    @ApiOperation(value = "查询评价部门别待审批个数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R evsDeptInfo(@ApiIgnore @CurrentUser User user,@RequestBody @ApiParam(name="查询条件对象",value="传入json格式",required=true) AffirmQuery affirmQuery) {
        affirmQuery.setUsername(user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=affirmEvsDept" , affirmQuery,R.class);
    }
}
