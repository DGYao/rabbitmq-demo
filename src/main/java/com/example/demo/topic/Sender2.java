package com.example.demo.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Component
public class Sender2 {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String context = "hi, i am message 2";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
    }
}
