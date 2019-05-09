package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_HA,durable = "true"),
        exchange = @Exchange(value = YAO_HEADERS_EXCHANGE,type = "headers",durable = "true"),arguments = {
        @Argument(name = "x-match", value = "any"),
        @Argument(name = "a", value = "a"),
        @Argument(name = "b", value = "b")
}))
public class ReceiverHA {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverHA.class);
    @RabbitHandler
    public void process(Object data){
        Message message = (Message) data;
        logger.info("ReceiverHA：{}",new String(message.getBody()));
    }
}
