package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixPaymentController {
    @Autowired
    private HystrixPaymentService hystrixPaymentService;

    @GetMapping("/payment/ok")
    public String ok(){
        return hystrixPaymentService.ok();
    }

    @GetMapping("/payment/timeout")
    public String timeout(){
        return hystrixPaymentService.timeout();
    }

    @GetMapping("/payment/circuitBreaker/{id}")
    public String circuitBreaker(@PathVariable("id")Integer id){
        return hystrixPaymentService.circuitBreaker(id);
    }
}
