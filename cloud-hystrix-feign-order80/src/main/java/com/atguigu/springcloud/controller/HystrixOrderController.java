package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "globalFallback")
public class HystrixOrderController {
    @Autowired
    private HystrixPaymentService hystrixPaymentService;


    @GetMapping("/consumer/payment/ok")
    public String ok() {
        return hystrixPaymentService.ok();
    }

    @GetMapping("/consumer/payment/timeout")
//    @HystrixCommand(commandKey = "timeout",fallbackMethod = "timeoutFallback") // 指定特定的fallback方法
    @HystrixCommand // 没有指定特定的fallback方法 则采用全局fallback方法
    public String timeout() {
        return hystrixPaymentService.timeout();
    }

    public String timeoutFallback() {
        return "timout fallback from order hystrix";
    }


    //==================fallback global test
    public String globalFallback() {
        return "globalFallback from order hystrix";
    }


    //=================circuit breaker test
    @GetMapping("/consumer/payment/circuitBreaker/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id) {
        return hystrixPaymentService.circuitBreaker(id);
    }

}
