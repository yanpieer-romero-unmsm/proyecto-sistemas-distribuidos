package com.unmsm.distributedsystems.inventorymanager.expose.web;

import com.google.gson.Gson;
import com.unmsm.distributedsystems.inventorymanager.dao.ArticleDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.dto.OrderProcessingDto;
import com.unmsm.distributedsystems.inventorymanager.service.async.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.unmsm.distributedsystems.inventorymanager.util.constant.Constants.INVENTORY_MANAGEMENT_TOPIC;
import static com.unmsm.distributedsystems.inventorymanager.util.constant.Constants.ORDER_PROCESSING_ERROR_TOPIC;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api-inventory-management/orders")
public class OrderProcessingController {

  private final Producer producer;
  private final ArticleDao dao;

  @PostMapping
  public void save(@Valid @RequestBody OrderProcessingDto orderProcessing) {
    List<ArticleDto> articles = orderProcessing.getArticles();
    AtomicInteger count = new AtomicInteger(0);
    Boolean isValidated = articles.stream()
        .filter(this::thereIsStock)
        .map(article -> {
          count.addAndGet(1);
          return article;
        })
        .map(article -> isOrderValidated(articles.size(), count.get(), article))
        .anyMatch(isValidate -> isValidate);

    if (isValidated) {
      producer.publish(new Gson().toJson(orderProcessing), INVENTORY_MANAGEMENT_TOPIC);
    } else {
      producer.publish(orderProcessing.getOrderId().toString(), ORDER_PROCESSING_ERROR_TOPIC);
    }
    producer.publish(new Gson().toJson(orderProcessing), INVENTORY_MANAGEMENT_TOPIC);
  }

  private Boolean thereIsStock(ArticleDto articleDto) {
    Integer actualStock = dao.findById(articleDto.getId()).get().getQuantity();
    Integer requiredQuantity = articleDto.getQuantity();
    return requiredQuantity <= actualStock;
  }

  private Boolean isOrderValidated(Integer totalArticles,
                                   Integer articlesValidated,
                                   ArticleDto article) {
    if (totalArticles == articlesValidated) {
      dao.updateStock(article.getQuantity(), article.getId());
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

}
