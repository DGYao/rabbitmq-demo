package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.QUEUE_A;
import static com.example.demo.RabbitConfig.ROUTING_KEY_A;
import static com.example.demo.RabbitConfig.YAO_DIRECT_EXCHANGE;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_A,durable = "true"),
        exchange = @Exchange(value = YAO_DIRECT_EXCHANGE,durable = "true"),key = ROUTING_KEY_A))
public class ReceiverDA {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverDA.class);
    @RabbitHandler
    public void process(String hello){
        logger.info("ReceiverDA1：{}",hello);
    }

    @RabbitHandler
    public void process2(byte[] message){
        logger.info("ReceiverDA2：{}",new String(message));
    }
}
