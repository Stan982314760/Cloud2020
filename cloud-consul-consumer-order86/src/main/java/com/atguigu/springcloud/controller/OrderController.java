package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    public static final String SERVICE_URL = "http://consul-payment-service";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/consul/payment")
    public String testConsul() {
        return restTemplate.getForObject(SERVICE_URL + "/consul/payment", String.class);
    }
}
