package com.unmsm.distributedsystems.reservation.dao.impl;

import com.unmsm.distributedsystems.reservation.dao.OrderDao;
import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;
import com.unmsm.distributedsystems.reservation.model.entity.ArticleEntity;
import com.unmsm.distributedsystems.reservation.repository.ArticleRepository;
import com.unmsm.distributedsystems.reservation.util.build.ArticleBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OrderDaoImpl implements OrderDao {

    private final ArticleRepository repository;
    private final ArticleBuilder articleBuilder;

    @Override
    public Optional<ArticleDto> findById(Integer id) {
        return repository.findById(id).map(articleBuilder::build);
    }

    @Override
    public void updateStock(Integer quantity, Integer articleId) {
        ArticleEntity articleToUpdate = repository.findById(articleId).get();
        Integer actualStock = articleToUpdate.getStock();
        articleToUpdate.setStock(actualStock - quantity);
        repository.save(articleToUpdate);
    }
}
