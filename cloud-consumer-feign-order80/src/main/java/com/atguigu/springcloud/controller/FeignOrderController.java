package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.service.FeignOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignOrderController {
    @Autowired
    private FeignOrderService feignOrderService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Integer id){
        return feignOrderService.getPayment(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String feignTimeout(){
        return feignOrderService.feignTimeout();
    }
}
