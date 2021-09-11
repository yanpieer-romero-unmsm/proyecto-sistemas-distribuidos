package com.unmsm.distributedsystems.inventorymanager.service;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;

import java.util.List;

public interface ReceivableService {

  List<ReceivableDto> findAll();

  ReceivableDto save(ReceivableDto receivableDto);
}
