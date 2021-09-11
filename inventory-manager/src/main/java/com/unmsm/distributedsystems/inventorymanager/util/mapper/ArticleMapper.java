package com.unmsm.distributedsystems.inventorymanager.util.mapper;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.entity.ArticleDocument;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

  public ArticleDto build(ArticleDocument article) {
    return ArticleDto.builder()
        .id(article.getId())
        .code(article.getCode())
        .name(article.getName())
        .unitPrice(article.getUnitPrice())
        .quantity(article.getStock())
        .build();
  }
}
