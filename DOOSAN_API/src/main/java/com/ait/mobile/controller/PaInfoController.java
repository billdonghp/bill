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
 * @Email 1377252306@qq.com
 * @Date 2018/7/11 12:09
 * @Version 1.0.0
 **/
@Api(tags = "6.工资信息查询")
@RestController
@RequestMapping(value = "/mobile/api")
public class PaInfoController extends BaseController {

    @PostMapping("/pa/{month}")
    @Authorization
    @ApiOperation(value = "审批")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R paInfo(@ApiIgnore @CurrentUser User user, @PathVariable String month) {
        Map map = new HashMap();
        map.put("username", user.getUsername());
        map.put("PA_MONTH", month);
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=paInfo" , map,R.class);
    }

}
