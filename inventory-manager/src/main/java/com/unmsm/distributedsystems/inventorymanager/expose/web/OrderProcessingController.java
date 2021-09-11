package com.unmsm.distributedsystems.inventorymanager.expose.web;

import com.google.gson.Gson;
import com.unmsm.distributedsystems.inventorymanager.model.dto.OrderProcessingDto;
import com.unmsm.distributedsystems.inventorymanager.service.async.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

import static com.unmsm.distributedsystems.inventorymanager.util.constant.Constants.INVENTORY_MANAGEMENT_TOPIC;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api-inventory-management/orders")
public class OrderProcessingController {

  private final Producer producer;

  @PostMapping
  public void save(@Valid @RequestBody OrderProcessingDto orderProcessing) {
    System.out.println("LLEGOAQUI");
    producer.publish(new Gson().toJson(orderProcessing), INVENTORY_MANAGEMENT_TOPIC);
  }

}
