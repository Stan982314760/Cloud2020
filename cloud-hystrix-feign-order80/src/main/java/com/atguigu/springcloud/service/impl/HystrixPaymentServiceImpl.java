package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.HystrixPaymentService;
import org.springframework.stereotype.Component;

@Component
public class HystrixPaymentServiceImpl implements HystrixPaymentService {

    @Override
    public String ok() {
        return "the payment server is down,please try again later. From Feign-Order Hystrix!";
    }

    @Override
    public String timeout() {
        return "the payment server is down,please try again later. From Feign-Order Hystrix!";
    }

    @Override
    public String circuitBreaker(Integer id) {
        return "the payment server is down,please try again later. From Feign-Order Hystrix!";
    }
}
