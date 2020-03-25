package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPayment(id);
        if (payment == null) {
            log.info("from payment 8002: id不存在");
            return new CommonResult(444, serverPort + " 你查找的id不存在", null);
        }

        return new CommonResult(200, serverPort + " 查询成功", payment);
    }

    /**
     * @param payment
     * @return
     * @RequestBody缺少,会报错!! 因为restTemplate的post请求会把请求体对象转为json串 所以要加上这个注解把json串转为javabean对象
     */
    @PostMapping("/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment) {
        if (payment.getSerial() == null)
            return new CommonResult(444, serverPort + " 你传入的参数有问题", null);

        paymentService.addPayment(payment);
        log.info("插入了一条记录: " + payment);
        return new CommonResult(200, serverPort + " 插入成功", payment);
    }


    //=================== DiscoveryClient API Test
    @GetMapping("/payment/discovery")
    public String testDiscoveryClient() {
        // 获取所有服务
        List<String> services = discoveryClient.getServices();
        System.out.println("eureka注册中心的服务有: " + services);

        // 获取特定名称服务
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        System.out.println("服务详细信息如下: ");
        instances.forEach(serviceInstance ->
                System.out.println(serviceInstance.getHost() + "\t" + serviceInstance.getInstanceId() + "\t" + serviceInstance.getUri()));

        return "sucess";
    }
}
