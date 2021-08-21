package com.unmsm.distributedsystems.reservation.service.impl;

import com.unmsm.distributedsystems.reservation.dao.InvoiceDao;
import com.unmsm.distributedsystems.reservation.model.dto.InvoiceDto;
import com.unmsm.distributedsystems.reservation.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceDao dao;

    @Override
    public List<InvoiceDto> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<InvoiceDto> findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public InvoiceDto save(InvoiceDto invoiceDto) {
        return dao.save(invoiceDto);
    }
}
