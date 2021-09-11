package com.unmsm.distributedsystems.inventorymanager.repository.nosql;

import com.unmsm.distributedsystems.inventorymanager.model.entity.document.ArticleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepositoryNoSql extends MongoRepository<ArticleDocument, Integer> {

  List<ArticleDocument> findAll();

  Optional<ArticleDocument> findById(Integer id);

  ArticleDocument save(ArticleDocument article);

}
