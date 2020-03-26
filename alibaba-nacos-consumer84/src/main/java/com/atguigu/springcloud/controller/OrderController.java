package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/sentinel/{id}")
    @SentinelResource(value = "sentinelTest", blockHandler = "sentinelBlockHandler", fallback = "sentinelFallback")
    public String sentinel(@PathVariable("id") Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("id不能为0");

        return paymentService.sentinel();
    }

    // 只要存在限流规则 且payment服务没挂掉就来这个方法
    // 如果id!=0 存在限流规则 但payment服务挂掉了 此时由Feign接口实现类处理降级
    public String sentinelBlockHandler(Integer id, BlockException e) {
        return "sentinel限流 降级方法";
    }

    // 当不存在限流规则且id=0时 不论payment服务有没有挂掉 都来这个方法
    public String sentinelFallback(Integer id, Throwable e) {
        return "运行期异常 降级方法";
    }
}
