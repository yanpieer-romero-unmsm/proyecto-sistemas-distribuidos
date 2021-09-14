package com.unmsm.distributedsystems.inventorymanager.repository;

import com.unmsm.distributedsystems.inventorymanager.model.entity.ArticleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryNoSql extends MongoRepository<ArticleDocument, Integer> {

  List<ArticleDocument> findAll();

  Optional<ArticleDocument> findById(Integer id);

  ArticleDocument save(ArticleDocument article);

}
