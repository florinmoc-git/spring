package patientmanagementsystem.pms.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfigQueuesAndBindings {
    @Autowired
    private AmqpAdmin admin;
    @Value("${rabbitmq.department.topic.exchange}")
    String departmentTopicExchange;
    @Value("#{${rabbitmq.department.routing.keys}}")
    private Map<String, String> routingKeys;
    @Value("#{${rabbitmq.department.request.queues}}")
    private Map<String, String> requestQueues;

    @PostConstruct
    public void init(){
        var departments = requestQueues.keySet();
        departments.forEach(department -> {
            Queue queue = new Queue(
                    requestQueues.get(department),
                    true,
                    false,
                    false
            );
            Binding binding = new Binding(
                    requestQueues.get(department),
                    Binding.DestinationType.QUEUE,
                    departmentTopicExchange,
                    routingKeys.get(department),
                    null
            );
            admin.declareQueue(queue);
            admin.declareBinding(binding);
        });
    }
}
