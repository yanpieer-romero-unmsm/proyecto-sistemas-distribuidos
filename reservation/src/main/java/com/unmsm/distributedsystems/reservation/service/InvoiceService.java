package com.unmsm.distributedsystems.reservation.service;

import com.unmsm.distributedsystems.reservation.model.dto.InvoiceDto;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<InvoiceDto> findAll();
    Optional<InvoiceDto> findById(Integer id);
    InvoiceDto save(InvoiceDto invoiceDto);
}
