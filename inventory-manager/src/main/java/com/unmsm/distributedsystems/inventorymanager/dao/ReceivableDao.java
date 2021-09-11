package com.unmsm.distributedsystems.inventorymanager.dao;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;

import java.util.List;

public interface ReceivableDao {

  List<ReceivableDto> findAll();

  ReceivableDto save(ReceivableDto receivableDto);
}
