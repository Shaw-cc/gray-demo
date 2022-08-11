package com.shaw.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: Shaw
 * @Date: 2022/8/11 23:58
 */
@RestController
public class OrderController {

    @Value("${app.version}")
    String version;

    @GetMapping("/price")
    public String sayHello(Integer price) {
        return "price:" + price + version;
    }


}
