package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    public static final String SERVICE_URL = "http://nacos-payment-service";
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/nacos/payment")
    public String nacosTest() {
        return restTemplate.getForObject(SERVICE_URL + "/nacos/payment", String.class);
    }
}
