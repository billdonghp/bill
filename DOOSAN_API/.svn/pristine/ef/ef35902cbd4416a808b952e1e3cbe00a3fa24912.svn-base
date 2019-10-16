package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.model.Affirm;
import com.ait.mobile.model.AffirmQuery;
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
@Api(tags = "5.信息审批")
@RestController
@RequestMapping(value = "/mobile/api")
public class AffirmController extends BaseController {

    @PostMapping("/affirm")
    @Authorization
    @ApiOperation(value = "审批")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R affirm(@ApiIgnore @CurrentUser User user, @RequestBody Affirm affirm) {
        affirm.setUsername(user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=affirm" , affirm,R.class);
    }

    @PostMapping("/badges")
    @Authorization
    @ApiOperation(value = "提醒待审批个数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R badges(@ApiIgnore @CurrentUser User user) {
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=badges" , user,R.class);
    }
}
