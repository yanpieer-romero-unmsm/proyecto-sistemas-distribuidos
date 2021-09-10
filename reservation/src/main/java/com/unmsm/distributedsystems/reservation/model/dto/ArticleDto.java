package com.unmsm.distributedsystems.reservation.model.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@Data
public class ArticleDto implements Serializable {

  private Integer id;
  private String code;
  private String name;
  private Double unitPrice;
  private Integer quantity;
}
