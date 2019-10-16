package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.model.AffirmQuery;
import com.ait.mobile.model.ApplyQuery;
import com.ait.mobile.model.User;
import com.ait.mobile.utils.R;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @ClassName LoginController
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2018/7/11 12:09
 * @Version 1.0.0
 **/
@Api(tags = "3.查询申请信息")
@RestController
@RequestMapping(value = "/mobile/api")
public class ApplyInfoController extends BaseController {

    @PostMapping("/applyInfo")
    @Authorization
    @ApiOperation(value = "信息申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    })
    public R applyInfo(@ApiIgnore @CurrentUser User user, @RequestBody @ApiParam(name="查询条件对象",value="传入json格式",required=true) ApplyQuery applyQuery) {
        applyQuery.setUsername(user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=applyInfo" , applyQuery,R.class);
    }

}
