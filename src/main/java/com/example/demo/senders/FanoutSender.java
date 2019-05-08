package com.example.demo.senders;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.YAO_FANOUT_EXCHANGE;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
//        String content = "hello,i am fanout mode,send string";
//        this.amqpTemplate.convertAndSend(YaoFanoutExchange,"", content);
        String content2 = "hello,i am fanout mode,send byte[]";
        this.amqpTemplate.convertAndSend(YAO_FANOUT_EXCHANGE,"", content2.getBytes());
    }
}
