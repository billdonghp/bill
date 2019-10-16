package com.ait.mobile.authorization.manager.impl;

import com.ait.mobile.authorization.manager.TokenManager;
import com.ait.mobile.authorization.model.TokenModel;
import com.ait.mobile.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private StringRedisTemplate redis;

    public TokenModel createToken(String username) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(username, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(username).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public TokenModel getToken(String authentication) {

        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String username = param[0];
        String token = param[1];
        return new TokenModel(username, token);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUsername()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUsername()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(String username) {
        redis.delete(username);
    }
}
