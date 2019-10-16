package com.ait.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户数据的domain类
 * @author ScienJus
 * @date 2015/7/31.
 */
@Data
@ApiModel(value="密码修改",description="修改密码用的对象")
public class ModifyPassword {

    //用户名
    @ApiModelProperty(value="旧密码",name="oldPassword",required=true,example="123456" ,dataType = "string")
    private String oldPassword;

    //密码
    @ApiModelProperty(value="新密码",name="newPassword",required=true ,example="654321" ,dataType = "string")
    private String newPassword;
}
