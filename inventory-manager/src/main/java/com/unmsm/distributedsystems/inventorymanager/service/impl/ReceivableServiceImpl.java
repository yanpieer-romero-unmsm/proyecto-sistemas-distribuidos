package com.unmsm.distributedsystems.inventorymanager.service.impl;

import com.unmsm.distributedsystems.inventorymanager.dao.ReceivableDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;
import com.unmsm.distributedsystems.inventorymanager.service.ReceivableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReceivableServiceImpl implements ReceivableService {

  private final ReceivableDao dao;


  @Override
  public List<ReceivableDto> findAll() {
    return dao.findAll();
  }

  @Override
  public ReceivableDto save(ReceivableDto receivableDto) {
    return dao.save(receivableDto);
  }
}
