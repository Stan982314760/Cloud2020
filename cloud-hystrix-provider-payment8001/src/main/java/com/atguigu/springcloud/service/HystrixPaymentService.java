package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HystrixPaymentService {

    public String ok(){
        return Thread.currentThread().getName() + "\t ok";
    }


    // commandKey是要降级的方法名称 @HystrixProperty属性具体查看HystrixCommandProperties
    @HystrixCommand(commandKey="timeout",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")}, // 方法超过1秒 执行降级方法
            fallbackMethod = "timeoutFallback")
    public String timeout(){
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        return Thread.currentThread().getName() + "\t timeout";
    }

    // fallback方法的参数必须与原方法一模一样，不能多，也不能少
    public String timeoutFallback(){
        return "this is fallback for payment_timeout";
    }


    //=================Circuit Breaker Test
    @HystrixCommand(commandKey = "circuitBreaker",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 开启熔断机制
            @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "8"), // 统计周期默认10秒 一个Bucket代表1秒
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "4"), // 8秒内至少要有4个请求
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "6000"), // 熔断到半熔断休眠期为6秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 8秒内有3个异常 开启熔断
    },fallbackMethod = "circuitBreakerFallback")
    public String circuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("当前参数有误 id不能为负数");
        }

        return Thread.currentThread().getName() + "\t  circuit breaker";
    }

    // 熔断后 不会执行主逻辑 直接调用fallback方法
    public String circuitBreakerFallback(Integer id){
        return "当前参数有误 id不能为负数";
    }
}
