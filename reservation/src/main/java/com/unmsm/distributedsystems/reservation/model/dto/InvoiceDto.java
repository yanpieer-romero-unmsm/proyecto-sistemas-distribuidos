package com.unmsm.distributedsystems.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class InvoiceDto {

    Integer id;

    Integer clientId;

    Double igvTotal;

    Double invoiceTotal;
}
