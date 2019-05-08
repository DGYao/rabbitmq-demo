package com.example.demo.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutSenderTest {
    @Autowired
    Sender sender;
    @Test
    public void send() throws Exception {
        sender.send();
    }

}