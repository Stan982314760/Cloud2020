package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    public static final String SERVICE_URL = "http://CLOUD-PAYMENT-SERVICE"; // 从eureka注册中心 去找特定名的服务
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(SERVICE_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/add")
    public CommonResult addPayment(Payment payment) {
        CommonResult commonResult = restTemplate.postForObject(SERVICE_URL + "/payment/add", payment, CommonResult.class);
        return commonResult;
    }

    //==============DiscoveryClient test
    @GetMapping("/consumer/payment/discovery")
    public String testDiscoveryClient() {
        return restTemplate.getForObject(SERVICE_URL + "/payment/discovery", String.class);
    }
}
