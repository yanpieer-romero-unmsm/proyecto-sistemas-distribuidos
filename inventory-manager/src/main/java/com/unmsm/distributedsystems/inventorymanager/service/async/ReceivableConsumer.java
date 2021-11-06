package com.unmsm.distributedsystems.inventorymanager.service.async;

import com.google.gson.Gson;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;
import com.unmsm.distributedsystems.inventorymanager.service.ReceivableService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReceivableConsumer {

  private final ReceivableService receivableService;

  @KafkaListener(topics = "receivable_topic", groupId = "group_id")
  public void consume(String receivableMessage) {
    Gson gson = new Gson();
    ReceivableDto receivableDto = gson.fromJson(receivableMessage, ReceivableDto.class);
    receivableService.save(receivableDto);
  }
}
