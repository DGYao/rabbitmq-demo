package com.example.demo.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaodingguo on 2017/8/4.
 */
@Configuration
public class FanoutRabbitConfig {
    @Bean
    public Queue Aq(){
        return new Queue("fanout.a");
    }
    @Bean
    public Queue Bq(){
        return new Queue("fanout.b");
    }
    @Bean
    public Queue Cq(){
        return new Queue("fanout.c");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("FanoutExchange");
    }
    @Bean
    public Binding bindingAq(Queue Aq,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(Aq).to(fanoutExchange);
    }
    @Bean
    public Binding bindingBq(Queue Bq,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(Bq).to(fanoutExchange);
    }
    @Bean
    public Binding bindingCq(Queue Cq,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(Cq).to(fanoutExchange);
    }
}
