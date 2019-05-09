package com.example.demo.senders;

import com.example.demo.dto.HeaderMessagePostProcessor;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.YAO_DIRECT_EXCHANGE;
import static com.example.demo.RabbitConfig.YAO_HEADERS_EXCHANGE;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Component
public class HeaderSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String content = "hello,i am header mode,send string[]";
        HeaderMessagePostProcessor messagePostProcessor = new HeaderMessagePostProcessor();
        this.amqpTemplate.convertAndSend(YAO_HEADERS_EXCHANGE,"",content,messagePostProcessor);
    }
}
