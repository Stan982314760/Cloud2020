package com.atguigu.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyRoundRuleImpl implements MyRoundRule {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        int index = getNext(instances.size());
        return instances.get(index);
    }


    private int getNext(int size) {
        int current;
        int next;

        do {
            current = count.get();
            next = (current + 1) % size;
        } while (!count.compareAndSet(current, next)); // 当CAS比较不通过 循环操作

        return next;
    }
}
