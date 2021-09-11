package com.unmsm.distributedsystems.inventorymanager;

import com.unmsm.distributedsystems.inventorymanager.model.entity.document.ArticleDocument;
import com.unmsm.distributedsystems.inventorymanager.repository.nosql.ArticleRepositoryNoSql;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@AllArgsConstructor
@SpringBootApplication
public class InventoryManagerApplication implements CommandLineRunner {

  private MongoTemplate mongoTemplate;
  private ArticleRepositoryNoSql repository;

  public static void main(String[] args) {
    SpringApplication.run(InventoryManagerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    mongoTemplate.dropCollection("articles");

    repository.save(ArticleDocument.builder()
        .id(1)
        .code("0001")
        .name("Papel fotocopia millenium")
        .unitPrice(11.70)
        .stock(21)
        .build());

    repository.save(ArticleDocument.builder()
        .id(2)
        .code("0002")
        .name("Plumones dual brush pastel")
        .unitPrice(10.00)
        .stock(22)
        .build());

    repository.save(ArticleDocument.builder()
        .id(3)
        .code("0003")
        .name("Tableta gráfica huion")
        .unitPrice(13.00)
        .stock(23)
        .build());

    repository.save(ArticleDocument.builder()
        .id(4)
        .code("0004")
        .name("Block de dibujo especial")
        .unitPrice(19.90)
        .stock(24)
        .build());

    repository.save(ArticleDocument.builder()
        .id(5)
        .code("0005")
        .name("Tinta EPSON botellas T664")
        .unitPrice(131.60)
        .stock(25)
        .build());
  }
}
