package com.unmsm.distributedsystems.inventorymanager.repository.cache;

import com.unmsm.distributedsystems.inventorymanager.model.entity.hash.ReceivableHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivableRepositoryCache extends
    CrudRepository<ReceivableHash, Integer>,
    QueryByExampleExecutor<ReceivableHash> {

  List<ReceivableHash> findAll();

  ReceivableHash save(ReceivableHash article);
}
