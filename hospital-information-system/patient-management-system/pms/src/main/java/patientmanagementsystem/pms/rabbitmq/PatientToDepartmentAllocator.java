package patientmanagementsystem.pms.rabbitmq;

import com.his.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import patientmanagementsystem.pms.dtos.PatientToDepartmentAllocationRequest;

import java.util.Map;

/**
 * Allocates or re-allocates a patient to one of hospital's departments
 */
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

    public void sendAllocationMessage(Patient patient, String department){
        var allocationRequest = new PatientToDepartmentAllocationRequest(patient, department);
        logger.info(String.format("Patient sent -> %s", allocationRequest.patient()));
        rabbitTemplate.convertAndSend(
                departmentTopicExchange, routingKeys.get(allocationRequest.routingKey()),
                allocationRequest.patient());
    }
}
