package com.unmsm.distributedsystems.reservation.repository;

import com.unmsm.distributedsystems.reservation.model.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer> {

    List<ArticleEntity> findAll();
    Optional<ArticleEntity> findById(Integer id);
    ArticleEntity save(ArticleEntity articleEntity);
}
