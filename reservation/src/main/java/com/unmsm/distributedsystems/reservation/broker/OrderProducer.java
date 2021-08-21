package com.unmsm.distributedsystems.reservation.broker;

import static com.unmsm.distributedsystems.reservation.util.constant.Constants.ORDER_TOPIC;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Publicar.
     * @param orderRequestJson
     */
    public void publish(String orderRequestJson) {
        kafkaTemplate.send(ORDER_TOPIC, orderRequestJson);
    }
}
