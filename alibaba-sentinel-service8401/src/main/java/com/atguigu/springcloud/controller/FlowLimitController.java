package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.component.MyBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController { // 限流测试类

    @GetMapping("/test1")
    public String test1() {
        return "sentinel service limit test1";
    }


    //====================自定义BlockHandler测试
    @GetMapping("/test2")
    @SentinelResource(value = "test2", blockHandlerClass = MyBlockHandler.class, blockHandler = "blockHandler") // 自定义类和方法名都得指定
    public String test2() {
        return "sentinel service limit test2";
    }


    //======================RT 降级测试
    @GetMapping("/test/fallback")
    public String test3() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sentinel service fallback test";
    }

    //======================异常数 降级测试
    @GetMapping("/test/error")
    public String test4() {
        int a = 10 / 0;
        return "sentinel service fallback test";
    }

    //======================热点key 降级测试
    // 热点key规则 必须与@SentinelResource一起使用 资源名称是@SentinelResource指定的value
    @GetMapping("/test/hotKey")
    @SentinelResource(value = "hotKey", blockHandler = "hotKeyFallback")
    public String test4(@RequestParam(name = "p1", required = false) String p1,
                        @RequestParam(name = "p2", required = false) String p2) {
        return "hotkey test, p1=" + p1;
    }

    // blockHandler指定的方法 参数必须要多一个BlockException类型参数
    public String hotKeyFallback(String p1, String p2, BlockException e) {
        return "hot key fallback";
    }
}
