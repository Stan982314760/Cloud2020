package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/consul/payment")
    public String testConsul(){
        return "consul registry, server port 8006";
    }
}
