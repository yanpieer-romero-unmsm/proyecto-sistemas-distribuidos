package com.unmsm.distributedsystems.inventorymanager.service;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

  List<ArticleDto> findAll();
}
