package com.unmsm.distributedsystems.inventorymanager.dao.impl;

import com.unmsm.distributedsystems.inventorymanager.dao.ArticleDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.entity.document.ArticleDocument;
import com.unmsm.distributedsystems.inventorymanager.repository.nosql.ArticleRepositoryNoSql;
import com.unmsm.distributedsystems.inventorymanager.util.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArticleDaoImpl implements ArticleDao {

    private final ArticleRepositoryNoSql repository;
    private final ArticleMapper articleMapper;

    @Override
    public Optional<ArticleDto> findById(Integer id) {
        return repository.findById(id).map(articleMapper::buildDocumentToDto);
    }

    @Override
    public List<ArticleDto> findAll() {
        return repository.findAll().stream().map(articleMapper::buildDocumentToDto)
            .collect(Collectors.toList());
    }

    @Override
    public void updateStock(Integer quantity, Integer articleId) {
        ArticleDocument articleToUpdate = repository.findById(articleId).get();
        Integer actualStock = articleToUpdate.getStock();
        articleToUpdate.setStock(actualStock - quantity);
        repository.save(articleToUpdate);
    }

}
