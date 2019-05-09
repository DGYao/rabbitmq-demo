package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/3.
 * 消费YAO_TOPIC_EXCHANGE----QUEUE_A----ROUTING_KEY_A
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_TA,durable = "true"),
        exchange = @Exchange(value = YAO_TOPIC_EXCHANGE,type = "topic",durable = "true"),key = ROUTING_KEY_A))
public class ReceiverTA {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverTA.class);
    @RabbitHandler
    public void process(String hello){
        logger.info("ReceiverTA1-{}：{}",ROUTING_KEY_A,hello);
    }

    @RabbitHandler
    public void process2(byte[] message){
        logger.info("ReceiverTA2-{}：{}",ROUTING_KEY_A,new String(message));
    }
}
