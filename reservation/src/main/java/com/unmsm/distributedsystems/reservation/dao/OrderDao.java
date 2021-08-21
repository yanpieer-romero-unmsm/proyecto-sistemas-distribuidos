package com.unmsm.distributedsystems.reservation.dao;

import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Optional<ArticleDto> findById(Integer id);

    List<ArticleDto> findAll();

    void updateStock(Integer quantity, Integer articleId);

}
