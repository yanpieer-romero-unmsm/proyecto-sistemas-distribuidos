package com.unmsm.distributedsystems.inventorymanager.dao;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {

  Optional<ArticleDto> findById(Integer id);

  void updateStock(Integer quantity, Integer articleId);

  List<ArticleDto> findAll();
}
