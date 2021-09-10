package com.unmsm.distributedsystems.inventorymanager.service.impl;

import com.unmsm.distributedsystems.inventorymanager.dao.ArticleDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private ArticleDao dao;

  @Override
  public List<ArticleDto> findAll() {
    return dao.findAll();
  }

}
