package com.unmsm.distributedsystems.inventorymanager.model.entity.hash;

import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ClientDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("receivable")
public class ReceivableHash implements Serializable {

  @Id
  Integer orderId;

  String invoiceId;

  Double totalIgv;

  Double totalInvoice;

  String paymentDate;

  ClientDto client;

  List<ArticleDto> articles;
}
