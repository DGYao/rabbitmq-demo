package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/3.
 * 消费YAO_TOPIC_EXCHANGE----QUEUE_C----rk.#
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_B,durable = "true"),
        exchange = @Exchange(value = YAO_TOPIC_EXCHANGE,type = "topic",durable = "true"),key = ROUTING_KEY_T_MORE))
public class ReceiverTB {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverTB.class);
    @RabbitHandler
    public void process(String hello){
        logger.info("ReceiverTB1-{}：{}",ROUTING_KEY_T_MORE,hello);
    }

    @RabbitHandler
    public void process2(byte[] message){
        logger.info("ReceiverTB2-{}：{}",ROUTING_KEY_T_MORE,new String(message));
    }
}
