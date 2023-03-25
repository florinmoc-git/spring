package healthinformationsystem.his.rabbitmq;

import healthinformationsystem.his.dtos.PatientToDepartmentAllocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PatientToDepartmentAllocator {

    private static final Logger logger = LoggerFactory.getLogger(PatientToDepartmentAllocator.class);
    @Value("${rabbitmq.department.topic.exchange}")
    String departmentTopicExchange;
    @Value("#{${rabbitmq.department.routing.keys}}")
    private Map<String, String> routingKeys;

    private final RabbitTemplate rabbitTemplate;

    public PatientToDepartmentAllocator(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAllocationMessage(PatientToDepartmentAllocationRequest allocationRequest){
        logger.info(String.format("Patient sent -> %s", allocationRequest.getPatient()));
        rabbitTemplate.convertAndSend(
                departmentTopicExchange, routingKeys.get(allocationRequest.getRoutingKey()),
                allocationRequest.getPatient());
    }
}
