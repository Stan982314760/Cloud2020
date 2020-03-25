package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLOUD-PAYMENT-SERVICE") // 指定注册中心要调用的服务名称
public interface FeignOrderService {

    @GetMapping("/payment/get/{id}")
    CommonResult getPayment(@PathVariable("id") Integer id);

    @GetMapping("/payment/feign/timeout")
    String feignTimeout();
}
