package com.example.demo.redirct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息发送者
 * Created by yaodingguo on 2017/8/3.
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(int i){
            String context = i+"";
            System.out.println("Sender : " + context);
            this.amqpTemplate.convertAndSend("hello", context);
    }
}
