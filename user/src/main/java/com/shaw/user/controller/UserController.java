package com.shaw.user.controller;

import com.shaw.graycore.bean.InvocationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: TODO
 * @Author: Shaw
 * @Date: 2022/8/11 23:31
 */
@RestController
public class UserController {


    @Autowired
    RestTemplate restTemplate;

    @Value("${app.version}")
    String version;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello" + version;
    }

    @GetMapping("/order")
    public String order() {
        String msg = restTemplate.getForObject("http://order/price", String.class);
        return "hello world " + msg;
    }

    @GetMapping("/gray")
    public String grayOrder() {
        InvocationContext.setInvocation("teamId", 100);
        String msg = restTemplate.getForObject("http://order/price", String.class);
        return "hello world " + msg;
    }

}
