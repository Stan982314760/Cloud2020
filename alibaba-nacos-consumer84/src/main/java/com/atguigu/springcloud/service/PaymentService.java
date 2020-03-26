package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "alibaba-payment-service",fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping("/payment/sentinel")
    String sentinel();
}
