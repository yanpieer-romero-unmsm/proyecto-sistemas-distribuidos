package com.unmsm.distributedsystems.reservation.util.build;

import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;
import com.unmsm.distributedsystems.reservation.model.entity.ArticleEntity;
import org.springframework.stereotype.Component;

@Component
public class ArticleBuilder {

    public ArticleDto build(ArticleEntity articleEntity) {
        return ArticleDto.builder()
                .id(articleEntity.getId())
                .name(articleEntity.getName())
                .unitPrice(articleEntity.getUnitPrice())
                .stock(articleEntity.getStock())
                .build();
    }
}
