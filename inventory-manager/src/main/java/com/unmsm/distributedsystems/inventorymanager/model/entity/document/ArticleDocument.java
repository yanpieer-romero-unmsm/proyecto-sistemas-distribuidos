package com.unmsm.distributedsystems.inventorymanager.model.entity.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "article")
public class ArticleDocument {

  @Id
  private Integer id;

  private String code;

  private String name;

  private Double unitPrice;

  private Integer stock;

  private Double subtotal;

}
