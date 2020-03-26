package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String sentinel() {
        return "如果能看到这句话 证明payment服务挂了";
    }
}
