package com.unmsm.distributedsystems.reservation.util.build;

import com.unmsm.distributedsystems.reservation.model.dto.InvoiceDto;
import com.unmsm.distributedsystems.reservation.model.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceBuilder {

    public InvoiceDto buildDto(InvoiceEntity entity) {
        return InvoiceDto.builder()
                .id(entity.getId())
                .clientId(entity.getClientId())
                .igvTotal(entity.getIgvTotal())
                .invoiceTotal(entity.getInvoiceTotal())
                .build();
    }

    public InvoiceEntity buildEntity(InvoiceDto dto) {
        return InvoiceEntity.builder()
                .id(dto.getId())
                .clientId(dto.getClientId())
                .igvTotal(dto.getIgvTotal())
                .invoiceTotal(dto.getInvoiceTotal())
                .build();
    }
}
