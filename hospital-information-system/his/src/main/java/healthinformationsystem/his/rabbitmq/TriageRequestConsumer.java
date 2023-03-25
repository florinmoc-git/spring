package healthinformationsystem.his.rabbitmq;

import healthinformationsystem.his.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TriageRequestConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TriageRequestConsumer.class);
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.triage.request.queue}")
    private String triageRequestQueue;

    public TriageRequestConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //    @RabbitListener(queues = {"${rabbitmq.triage.request.queue}"})
    public Patient consumeTriageRequest(){
        Patient patient = null;
        Object message = rabbitTemplate.receiveAndConvert(triageRequestQueue);
        if (message != null) {
            patient = (Patient) message;
            logger.info(String.format("Patient received -> %s", patient));
        }
        return patient;
    }
}
