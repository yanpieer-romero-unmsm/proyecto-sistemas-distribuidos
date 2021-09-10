package com.unmsm.distributedsystems.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class OrderProcessingDto implements Serializable {

  Integer orderId;
  ClientDto client;
  List<ArticleDto> articles;

}
