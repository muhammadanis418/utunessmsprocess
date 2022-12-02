package com.rockville.utunessmsprocessing.v2.config;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration


public class ConsumerConfig {

   @Autowired
   private ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory( SimpleRabbitListenerContainerFactoryConfigurer configurer){
            //(MessageConverter converter,)( SimpleRabbitListenerContainerFactoryConfigurer configurer)

        SimpleRabbitListenerContainerFactory factory1 = new SimpleRabbitListenerContainerFactory();
        factory1.setConcurrentConsumers(4);
        factory1.setMaxConcurrentConsumers(8);
        factory1.setPrefetchCount(100);
        configurer.configure(factory1, connectionFactory);
        //factory1.setMessageConverter(converter);
        return factory1;
    }

}
