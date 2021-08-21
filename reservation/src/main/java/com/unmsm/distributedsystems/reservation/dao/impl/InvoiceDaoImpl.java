package com.unmsm.distributedsystems.reservation.dao.impl;

import com.unmsm.distributedsystems.reservation.dao.InvoiceDao;
import com.unmsm.distributedsystems.reservation.model.dto.InvoiceDto;
import com.unmsm.distributedsystems.reservation.repository.InvoiceRepository;
import com.unmsm.distributedsystems.reservation.util.build.InvoiceBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InvoiceDaoImpl implements InvoiceDao {

    private final InvoiceRepository repository;
    private final InvoiceBuilder builder;

    @Override
    public List<InvoiceDto> findAll() {
        return repository.findAll().stream()
                .map(builder::buildDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InvoiceDto> findById(Integer id) {
        return repository.findById(id).map(builder::buildDto);
    }

    @Override
    public InvoiceDto save(InvoiceDto invoiceDto) {
        return builder.buildDto(repository.save(builder.buildEntity(invoiceDto)));
    }
}
