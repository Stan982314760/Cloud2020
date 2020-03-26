package com.atguigu.springcloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.ZonedDateTime;

@SpringBootTest(classes = Gateway9527.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TimeGet {

    @Test
    public void testGetTime(){
        // 2020-03-26T21:02:43.182+08:00[Asia/Shanghai]
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime);
    }
}
