package com.unmsm.distributedsystems.inventorymanager.util.mapper;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ReceivableDto;
import com.unmsm.distributedsystems.inventorymanager.model.entity.hash.ReceivableHash;
import org.springframework.stereotype.Component;

@Component
public class ReceivableMapper {

  public ReceivableDto buildHashToDto(ReceivableHash receivable) {
    return ReceivableDto.builder()
        .orderId(receivable.getOrderId())
        .invoiceId(receivable.getInvoiceId())
        .totalIgv(receivable.getTotalIgv())
        .totalInvoice(receivable.getTotalInvoice())
        .paymentDate(receivable.getPaymentDate())
        .client(receivable.getClient())
        .articles(receivable.getArticles())
        .build();
  }

  public ReceivableHash buildDtoToHash(ReceivableDto receivable) {
    return ReceivableHash.builder()
        .orderId(receivable.getOrderId())
        .invoiceId(receivable.getInvoiceId())
        .totalIgv(receivable.getTotalIgv())
        .totalInvoice(receivable.getTotalInvoice())
        .paymentDate(receivable.getPaymentDate())
        .client(receivable.getClient())
        .articles(receivable.getArticles())
        .build();
  }

}
