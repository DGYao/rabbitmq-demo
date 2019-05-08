package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/3.
 * 消费YAO_TOPIC_EXCHANGE----QUEUE_B----rk.*
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_C,durable = "true"),
        exchange = @Exchange(value = YAO_TOPIC_EXCHANGE,type = "topic",durable = "true"),key = ROUTING_KEY_T_ONE))
public class ReceiverTC {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverTC.class);
    @RabbitHandler
    public void process(String hello){
        logger.info("ReceiverTC1-{}：{}",ROUTING_KEY_T_ONE,hello);
    }

    @RabbitHandler
    public void process2(byte[] message){
        logger.info("ReceiverTC2-{}：{}",ROUTING_KEY_T_ONE,new String(message));
    }
}
