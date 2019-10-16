package com.ait.mobile.controller;

import com.ait.mobile.authorization.annotation.Authorization;
import com.ait.mobile.authorization.annotation.CurrentUser;
import com.ait.mobile.authorization.manager.TokenManager;
import com.ait.mobile.authorization.model.TokenModel;
import com.ait.mobile.model.DooSanLogin;
import com.ait.mobile.model.ModifyPassword;
import com.ait.mobile.model.User;
import com.ait.mobile.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2018/7/11 12:09
 * @Version 1.0.0
 **/
@Api(tags = "1.认证相关")
@RestController
@RequestMapping(value = "/mobile/api")
public class LoginController extends BaseController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ObjectMapper mapper;

    @Value("${doosan.api.Appkey}")
    private String Appkey;

    @PostMapping("/login")
    @ApiOperation(value = "1.登录")
    public R login(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user ) {
        try {
            if (IsBase64.isBase64(user.getUsername()) && !"test".equals(user.getTest())) {
                // BASE64解密
                BASE64Decoder decoder = new BASE64Decoder();
                user.setUsername(AES.Encrypt(new String(decoder.decodeBuffer(user.getUsername()))));
                user.setPassword(new String(decoder.decodeBuffer(user.getPassword())));
            }else{
                user.setUsername(AES.Encrypt(user.getUsername()));
            }
            R r = this.restTemplate.postForObject(HR_API_URL + "/api?operation=checkLogin" , user,R.class);

            if(r != null && "200".equals(r.get("resultCode").toString())){
                user.setUsername(AES.Encrypt(r.get("username").toString()));
                //生成一个token，保存用户登录状态
                TokenModel model = tokenManager.createToken(user.getUsername());
                return R.ok().put(Constants.AUTHORIZATION, model.toString());
            }else{
                return R.error("登陆失败");
            }
        } catch (Exception e) {
            return R.error("登陆失败");
        }
    }

    @PostMapping("/logout")
    @Authorization
    @ApiOperation(value = "2.退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public R logout(@ApiIgnore @CurrentUser User user) {
        tokenManager.deleteToken(user.getUsername());
        return R.ok();
    }

    @PostMapping("/modifyPassword")
    @Authorization
    @ApiOperation(value = "3.密码修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "1:认证信息", required = true, dataType = "string", paramType = "header")
    })
    public R modifyPassword(@ApiIgnore @CurrentUser User user, @RequestBody @ApiParam(name="密码对象",value="传入json格式",required=true)  ModifyPassword modifyPassword) {
        Map map = new HashMap();
        map.put("oldPassword", modifyPassword.getOldPassword());
        map.put("newPassword", modifyPassword.getNewPassword());
        map.put("password", modifyPassword.getOldPassword());
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=modifyPassword" , map,R.class);
    }

    /*@PostMapping("/resetPassword")
    @ApiOperation(value = "4.重置所有人密码为123456（测试阶段使用）")
    public R modifyPassword() {
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=resetPassword" , null,R.class);
    }*/

    /*@PostMapping("/getLoginInfo")
    @ApiOperation(value = "5.获取账号密码信息")
    public R getLoginInfo() {
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=getLoginInfo" , null,R.class);
    }*/


    @PostMapping("/userInfo")
    @Authorization
    @ApiOperation(value = "4.查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "cpnyId", value = "法人代码" , dataType = "string", paramType = "query")
    })
    public R userInfo(@ApiIgnore @CurrentUser User user, @RequestParam(required = false) String cpnyId) {
        Map map = new HashMap();
        map.put("cpnyId", cpnyId);
        map.put("username", user.getUsername());
        return this.restTemplate.postForObject(HR_API_URL + "/api?operation=userInfo" , map,R.class);
    }


    @PostMapping("/loginForWorker")
    @ApiOperation(value = "6.工人认证（斗掌天下app使用）")
    public R loginForWorker(@RequestBody @ApiParam(name="斗掌天下工人认证对象",value="传入json格式",required=true) DooSanLogin dooSanLogin ) {

        if (!Appkey.equals(dooSanLogin.getAppkey())) {
            return R.error("Appkey error");
        }
        String json = AES.decryptAES(dooSanLogin.getAesParams());

        //解密失败
        if (json == null || "".equals(json)) {
            return R.error("AesParams 解密异常");
        }

        //json转user
        User user = null;
        try {
            user = mapper.readValue(json, User.class);
        } catch (IOException e) {
            return R.error("AesParams 异常:" + e.getMessage());
        }

        //认证
        try {
            user.setUsername(AES.Encrypt(user.getUsername()));
        } catch (Exception e) {
            return R.error("username 加密异常");
        }
        R r = this.restTemplate.postForObject(HR_API_URL + "/api?operation=checkLoginForWorker" , user,R.class);

        if(r != null && "200".equals(r.get("resultCode").toString())){
            return R.ok("认证成功");
        }else{
            return R.error("认证失败");
        }
    }


    @PostMapping("/checkToken")
    @Authorization
    @ApiOperation(value = "5.token认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public R checkToken(@ApiIgnore @CurrentUser User user) {
        return R.ok();
    }
}
