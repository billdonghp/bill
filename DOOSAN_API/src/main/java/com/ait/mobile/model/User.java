package com.ait.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户数据的domain类
 * @author ScienJus
 * @date 2015/7/31.
 */
@Data
@ApiModel(value="登录对象",description="登录用的用户对象user")
public class User {

    //用户名
    @ApiModelProperty(value="用户名",name="username",required=true,example="ic9530774")
    private String username;

    //密码
    @ApiModelProperty(value="密码",name="password",required=true ,example="123456")
    private String password;

    //密码
    @ApiModelProperty(value="测试登录",name="test",required=true ,example="test")
    private String test;
}
