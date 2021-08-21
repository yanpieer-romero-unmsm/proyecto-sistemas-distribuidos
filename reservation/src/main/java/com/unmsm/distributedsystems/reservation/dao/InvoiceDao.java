package com.unmsm.distributedsystems.reservation.dao;

import com.unmsm.distributedsystems.reservation.model.dto.InvoiceDto;

import java.util.List;
import java.util.Optional;

public interface InvoiceDao {

    List<InvoiceDto> findAll();
    Optional<InvoiceDto> findById(Integer id);
    InvoiceDto save(InvoiceDto invoiceDto);
}
