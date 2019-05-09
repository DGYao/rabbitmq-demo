package com.example.demo;

import com.example.demo.senders.DirectSender;
import com.example.demo.senders.FanoutSender;
import com.example.demo.senders.TopicSender;
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
public class SenderTest {
    @Autowired
    FanoutSender fanoutSender;
    @Autowired
    DirectSender directSender;
    @Autowired
    TopicSender topicSender;
    @Test
    public void FSend() throws Exception {
        fanoutSender.send();
    }
    @Test
    public void DSend() throws Exception {
        directSender.send();
    }
    @Test
    public void TSend() throws Exception {
        topicSender.send();
    }

}