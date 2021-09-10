package com.unmsm.distributedsystems.inventorymanager.util.mapper;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.entity.ArticleEntity;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

  public ArticleDto build(ArticleEntity articleEntity) {
    return ArticleDto.builder()
        .id(articleEntity.getId())
        .name(articleEntity.getName())
        .unitPrice(articleEntity.getUnitPrice())
        .quantity(articleEntity.getStock())
        .build();
  }
}
