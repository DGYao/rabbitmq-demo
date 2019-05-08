package com.example.demo.senders;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Component
public class DirectSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
//        String content = "hello,i am direct mode,send string";
//        this.amqpTemplate.convertAndSend(YAO_DIRECT_EXCHANGE,"", content);
        String content2 = "hello,i am direct mode,send byte[]";
        this.amqpTemplate.convertAndSend(YAO_DIRECT_EXCHANGE,ROUTING_KEY_A, content2.getBytes());
    }
}
