package com.unmsm.distributedsystems.reservation.repository;

import com.unmsm.distributedsystems.reservation.model.entity.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Integer> {

    List<InvoiceEntity> findAll();
    Optional<InvoiceEntity> findById(Integer id);
    InvoiceEntity save(InvoiceEntity entity);
}
