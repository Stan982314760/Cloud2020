package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/nacos/payment")
    public String nacosTest(){
        return "nacos registry, server port is " + serverPort;
    }
}
