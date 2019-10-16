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
@ApiModel(value="斗掌天下工人登录认证对象")
public class DooSanLogin {

    //用户名
    @ApiModelProperty(value="传入参数 Json 字符串 AES 加密后的传参",required=true)
    private String AesParams;

    //密码
    @ApiModelProperty(value="接入方唯一 key",required=true )
    private String Appkey;

}
