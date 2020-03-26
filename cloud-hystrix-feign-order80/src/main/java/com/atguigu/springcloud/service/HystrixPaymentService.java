package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.HystrixPaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "HYSTRIX-PAYMENT-SERVICE",fallback = HystrixPaymentServiceImpl.class)
public interface HystrixPaymentService {

    @GetMapping("/payment/ok")
    String ok();

    /**
     * 这个方法有Feign自身的降级处理 在controller也标注了@HystrixCommand处理
     * 当两者都存在时 优先调用@HystrixCommand指定的特定降级方法或全局降级方法
     * @return
     */
    @GetMapping("/payment/timeout")
    String timeout();


    //===========payment 熔断方法测试
    @GetMapping("/payment/circuitBreaker/{id}")
    String circuitBreaker(@PathVariable("id")Integer id);
}
