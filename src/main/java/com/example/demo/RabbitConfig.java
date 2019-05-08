package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public final static String ACK_FACTORY_BEAN_NAME = "ACKMQContainerFactory";

    public final static String YAO_FANOUT_EXCHANGE = "YaoFanoutExchange";
    public final static String YAO_DIRECT_EXCHANGE = "YaoDirectExchange";
    public final static String YAO_TOPIC_EXCHANGE = "YaoTopicExchange";
    public final static String YAO_HEADERS_EXCHANGE = "YaoHeadersExchange";

    public final static String QUEUE_A = "yao.a";
    public final static String QUEUE_B = "yao.b";
    public final static String QUEUE_C = "yao.c";

    public final static String ROUTING_KEY_A = "rk.a";
    public final static String ROUTING_KEY_B = "rk.b.c";
    public final static String ROUTING_KEY_T_MORE = "rk.#";//#匹配多个单词，以.分隔，为一个单词
    public final static String ROUTING_KEY_T_ONE = "rk.*";//$匹配一个单词



    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setJsonObjectMapper(objectMapper);
        return converter;
    }

    @Bean(ACK_FACTORY_BEAN_NAME)
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleRabbitListenerContainerFactory.setMessageConverter(messageConverter);
        return simpleRabbitListenerContainerFactory;
    }
}
