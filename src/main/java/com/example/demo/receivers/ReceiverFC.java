package com.example.demo.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demo.RabbitConfig.QUEUE_FC;
import static com.example.demo.RabbitConfig.YAO_FANOUT_EXCHANGE;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_FC,durable = "true"),
        exchange = @Exchange(value = YAO_FANOUT_EXCHANGE,type = "fanout",durable = "true")))
public class ReceiverFC {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverFC.class);
    @RabbitHandler
    public void process(String hello){
        logger.info("ReceiverFC1：{}",hello);
    }

    @RabbitHandler
    public void process2(byte[] message){
        logger.info("ReceiverFC2：{}",new String(message));
    }
}
