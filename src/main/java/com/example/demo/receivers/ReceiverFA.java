package com.example.demo.receivers;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.demo.RabbitConfig.*;

/**
 * Created by yaodingguo on 2017/8/3.
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_FA,durable = "true"),
            exchange = @Exchange(value = YAO_FANOUT_EXCHANGE,type = "fanout",durable = "true")),containerFactory = ACK_FACTORY_BEAN_NAME)
public class ReceiverFA {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverFA.class);

    @RabbitHandler
    public void process1(String content,Message message, Channel channel){
        logger.info("ReceiverFA1:{}" ,content);
        answerExchange(message, channel);
    }

    @RabbitHandler
    public void process2(byte[] content,Message message, Channel channel){
        logger.info("ReceiverFA2:{}" ,new String(content));
        answerExchange(message, channel);
    }

    public void answerExchange(Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        logger.info("deliveryTag:{}",deliveryTag);
        String data = null;
        try {
            data = new String(message.getBody());
            channel.basicAck(deliveryTag,false);
        }catch (IOException e){
            logger.error("ack异常,message:{}",data,e);
        } catch (Exception e){
            logger.error("业务逻辑出现异常",e);
            try {
                channel.basicNack(deliveryTag,false,false);//第二个参数是是否操作deliveryTag之前的消息，第三个是是否重新放入队列
//                channel.basicReject(deliveryTag,false);等同channel.basicNack(deliveryTag，false,false);只能处理单条消息
            } catch (IOException e1) {
                logger.error("nack失败，message:{}",data,e);
            }
        }finally {
            //记录失败日志
        }
    }
}
