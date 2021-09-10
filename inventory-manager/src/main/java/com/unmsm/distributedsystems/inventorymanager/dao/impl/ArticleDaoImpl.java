package com.unmsm.distributedsystems.inventorymanager.dao.impl;

import com.unmsm.distributedsystems.inventorymanager.dao.ArticleDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.entity.ArticleEntity;
import com.unmsm.distributedsystems.inventorymanager.repository.ArticleRepository;
import com.unmsm.distributedsystems.inventorymanager.util.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArticleDaoImpl implements ArticleDao {

    private final ArticleRepository repository;
    private final ArticleMapper articleMapper;

    @Override
    public Optional<ArticleDto> findById(Integer id) {
        return repository.findById(id).map(articleMapper::build);
    }

    @Override
    public List<ArticleDto> findAll() {
        return repository.findAll().stream().map(articleMapper::build)
            .collect(Collectors.toList());
    }

    @Override
    public void updateStock(Integer quantity, Integer articleId) {
        ArticleEntity articleToUpdate = repository.findById(articleId).get();
        Integer actualStock = articleToUpdate.getStock();
        articleToUpdate.setStock(actualStock - quantity);
        repository.save(articleToUpdate);
    }

}
