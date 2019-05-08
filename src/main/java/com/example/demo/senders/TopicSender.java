package com.example.demo.senders;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
//        String content = "hello,i am topic mode,send string";
//        this.amqpTemplate.convertAndSend(YAO_TOPIC_EXCHANGE,"", content);
        String content2 = "hello,i am topic mode,send byte[]";
//        this.amqpTemplate.convertAndSend(YAO_TOPIC_EXCHANGE,ROUTING_KEY_A, (content2+" {"+ROUTING_KEY_A+"}").getBytes());
        this.amqpTemplate.convertAndSend(YAO_TOPIC_EXCHANGE,ROUTING_KEY_B, (content2+" {"+ROUTING_KEY_B+"}").getBytes());
    }
}
