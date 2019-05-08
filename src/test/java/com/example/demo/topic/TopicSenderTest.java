package com.example.demo.topic;

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
public class TopicSenderTest {
    @Autowired
    Sender1 sender1;
    @Autowired
    Sender2 sender2;
    @Test
    public void send(){
        sender1.send();
        sender2.send();
    }

}