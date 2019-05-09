package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    public final static String ACK_FACTORY_BEAN_NAME = "ACKMQContainerFactory";

    public final static String YAO_FANOUT_EXCHANGE = "YaoFanoutExchange";
    public final static String YAO_DIRECT_EXCHANGE = "YaoDirectExchange";
    public final static String YAO_TOPIC_EXCHANGE = "YaoTopicExchange";
    public final static String YAO_HEADERS_EXCHANGE = "YaoHeadersExchange";

    public final static String QUEUE_FA = "yao.f.a";
    public final static String QUEUE_FB = "yao.f.b";
    public final static String QUEUE_FC = "yao.f.c";

    public final static String QUEUE_DA = "yao.d.a";

    public final static String QUEUE_TA = "yao.t.a";
    public final static String QUEUE_TB = "yao.t.b";
    public final static String QUEUE_TC = "yao.t.c";

    public final static String QUEUE_HA = "yao.h.a";


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

    @Bean
    @Primary
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory,MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        //需配置spring.rabbitmq.publisher-confirms: true,ConfirmCallback才会生效
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if (b) {//ack成功时correlationData、s为null
                    logger.info("消息确认成功");
                } else {
                    logger.error("消息确认失败");
                }
            }
        });
        rabbitTemplate.setMandatory(true);//设置了true，ReturnCallback才会生效，false的话不会把消息传回来，直接丢弃，默认false
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                logger.error("路由不到交换机或route-key");
            }
        });
        return rabbitTemplate;
    }
}
