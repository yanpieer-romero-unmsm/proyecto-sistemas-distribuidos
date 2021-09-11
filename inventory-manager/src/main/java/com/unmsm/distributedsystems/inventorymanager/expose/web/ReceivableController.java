package com.unmsm.distributedsystems.inventorymanager.expose.web;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;
import com.unmsm.distributedsystems.inventorymanager.service.ReceivableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api-inventory-management/receivables")
public class ReceivableController {

  private final ReceivableService service;

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ReceivableDto>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }
}
