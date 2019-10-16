package com.ait.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2019/7/17 18:36
 * @Version 1.0.0
 **/
public class BaseController {

    @Autowired
    protected RestTemplate restTemplate;

    /**
     * web service url
     */
    @Value("${hr.api.url}")
    protected String HR_API_URL;
}
