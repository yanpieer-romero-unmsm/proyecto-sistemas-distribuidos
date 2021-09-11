package com.unmsm.distributedsystems.inventorymanager.dao.impl;

import com.unmsm.distributedsystems.inventorymanager.dao.ReceivableDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;
import com.unmsm.distributedsystems.inventorymanager.repository.cache.ReceivableRepositoryCache;
import com.unmsm.distributedsystems.inventorymanager.util.mapper.ReceivableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReceivableDaoImpl implements ReceivableDao  {

  private final ReceivableRepositoryCache repository;
  private final ReceivableMapper mapper;

  @Override
  public List<ReceivableDto> findAll() {
    return repository.findAll().stream().map(mapper::buildHashToDto)
        .collect(Collectors.toList());
  }

  @Override
  public ReceivableDto save(ReceivableDto receivableDto) {
    return mapper.buildHashToDto(repository.save(mapper.buildDtoToHash(receivableDto)));
  }
}
