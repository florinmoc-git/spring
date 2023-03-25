package healthinformationsystem.his.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.department.topic.exchange}")
    String departmentTopicExchange;
    @Value("${rabbitmq.triage.request.queue}")
    private String triageRequestQueue;
    @Value("${rabbitmq.cardiology.request.queue}")
    private String cardiologyRequestQueue;
    @Value("${rabbitmq.ophthalmology.request.queue}")
    private String ophthalmologyRequestQueue;
    @Value("#{${rabbitmq.department.routing.keys}}")
    private Map<String, String> routingKeys;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(departmentTopicExchange);
    }

    @Bean
    public Queue triageRequestQueue() {
        return new Queue(triageRequestQueue);
    }
    @Bean
    public Queue cardiologyRequestQueue() {
        return new Queue(cardiologyRequestQueue);
    }
    @Bean
    public Queue ophthalmologyRequestQueue() {
        return new Queue(ophthalmologyRequestQueue);
    }
    @Bean
    public Binding triageBinding() {
        return BindingBuilder
                .bind(triageRequestQueue())
                .to(topicExchange())
                .with(routingKeys.get("triage"));
    }
    @Bean
    public Binding cardiologyBinding() {
        return BindingBuilder
                .bind(cardiologyRequestQueue())
                .to(topicExchange())
                .with(routingKeys.get("cardiology"));
    }

    @Bean
    public Binding ophthalmologyBinding() {
        return BindingBuilder
                .bind(ophthalmologyRequestQueue())
                .to(topicExchange())
                .with(routingKeys.get("ophthalmology"));
    }


    @Bean
    public MessageConverter messageConverter(){
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

//    @Bean
//    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }

//    @DependsOn("amqpAdmin")
//    @PostConstruct
//    public void initQueues(){
//        var amqpAdmin = context.getBean(AmqpAdmin.class);
//        Queue queue = new Queue("triageRequestQueue", true, false, false);
//        Binding binding = new Binding("triageRequestQueue", Binding.DestinationType.QUEUE, departmentTopicExchange, "routingKey", null);
//        amqpAdmin.declareQueue(queue);
//        amqpAdmin.declareBinding(binding);
//    }

}
