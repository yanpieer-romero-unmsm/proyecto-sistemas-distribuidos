package com.unmsm.distributedsystems.reservation.service;

import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

  List<ArticleDto> findAll();
}
