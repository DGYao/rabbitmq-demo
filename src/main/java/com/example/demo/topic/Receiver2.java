package com.example.demo.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(queues = "topic.messages")
public class Receiver2 {
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver2："+hello);
    }
}
