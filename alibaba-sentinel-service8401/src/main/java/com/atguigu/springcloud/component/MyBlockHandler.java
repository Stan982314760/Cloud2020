package com.atguigu.springcloud.component;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

@Component
public class MyBlockHandler {

    // 注意 必须是static类型的方法 同时必须有BlockException参数
    public static String blockHandler(BlockException e){
        return "customer block handler";
    }
}
