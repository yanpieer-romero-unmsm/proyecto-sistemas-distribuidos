package com.unmsm.distributedsystems.reservation.service.async;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InventoryManagerProducer {

  private KafkaTemplate<String, String> kafkaTemplate;

  /**
   * Publicar.
   *
   * @param orderRequestJson
   */
  public void publish(String orderRequestJson, String topic) {
    kafkaTemplate.send(topic, orderRequestJson);
  }
}
