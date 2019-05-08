package com.example.demo.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Component("fanoutSender")
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String context = "send to every body";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("FanoutExchange","", context);
    }
}
