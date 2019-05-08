package com.example.demo.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(queues = "fanout.a")
public class ReceiverA {
    @RabbitHandler
    public void process(String hello){
        System.out.println("ReceiverA："+hello);
    }
}
