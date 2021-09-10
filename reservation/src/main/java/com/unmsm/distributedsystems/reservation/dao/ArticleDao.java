package com.unmsm.distributedsystems.reservation.dao;

import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {

  Optional<ArticleDto> findById(Integer id);

  void updateStock(Integer quantity, Integer articleId);

  List<ArticleDto> findAll();
}
