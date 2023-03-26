package healthinformationsystem.his.rabbitmq;

import healthinformationsystem.his.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AllocationRequestConsumer {
    private static final Logger logger = LoggerFactory.getLogger(AllocationRequestConsumer.class);

    private final RabbitTemplate rabbitTemplate;

    public AllocationRequestConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Patient consumeAllocationRequest(String departmentQueue){
        Patient patient = null;
        Object message = rabbitTemplate.receiveAndConvert(departmentQueue);
        if (message != null) {
            patient = (Patient) message;
            logger.info(String.format("Patient received -> %s", patient));
        }
        return patient;
    }
}
