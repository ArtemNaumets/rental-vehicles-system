package com.naumets.vehicle.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleHireProducer {

    private final KafkaTemplate<String, HireConfirmation> kafkaTemplate;

    public void sendHireConfirmation(HireConfirmation hireConfirmation){
        log.info("------------> VehicleHireProducer hireConfirmation:" + hireConfirmation);
        Message<HireConfirmation> message = MessageBuilder
                .withPayload(hireConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "vehicle-hire-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
