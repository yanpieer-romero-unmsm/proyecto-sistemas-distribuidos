package com.unmsm.distributedsystems.inventorymanager.model.dto;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@Data
public class ReceivableDto implements Serializable {

  Integer orderId;

  String invoiceId;

  Double totalIgv;

  Double totalInvoice;

  String paymentDate;

  ClientDto client;

  List<ArticleDto> articles;
}
