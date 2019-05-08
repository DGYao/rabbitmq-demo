package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_B,durable = "true"),
        exchange = @Exchange(value = YAO_FANOUT_EXCHANGE,type = "fanout",durable = "true")))
public class ReceiverFB {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverFB.class);
    @RabbitHandler
    public void process1(String hello){
        logger.info("ReceiverFB1：{}",hello);
    }

    @RabbitHandler
    public void process2(byte[] message){
        logger.info("ReceiverFB2：{}",new String(message));
    }
}
