package com.unmsm.distributedsystems.reservation.service.impl;

import com.unmsm.distributedsystems.reservation.dao.ArticleDao;
import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;
import com.unmsm.distributedsystems.reservation.service.ArticleService;
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
