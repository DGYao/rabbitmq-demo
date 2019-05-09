package com.example.demo.dto;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

import java.util.Map;

public class HeaderMessagePostProcessor implements MessagePostProcessor{
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        headers.put("a","a");
        return message;
    }
}
