package com.unmsm.distributedsystems.inventorymanager.service.async;

import static com.unmsm.distributedsystems.inventorymanager.util.constant.Constants.INVENTORY_MANAGEMENT_TOPIC;
import static com.unmsm.distributedsystems.inventorymanager.util.constant.Constants.ORDER_PROCESSING_ERROR_TOPIC;

import com.google.gson.Gson;
import com.unmsm.distributedsystems.inventorymanager.dao.ArticleDao;
import com.unmsm.distributedsystems.inventorymanager.model.dto.ArticleDto;
import com.unmsm.distributedsystems.inventorymanager.model.dto.OrderProcessingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class InventoryManagerConsumer {

  private final ArticleDao dao;
  private final InventoryManagerProducer inventoryManagerProducer;

  @KafkaListener(topics = "order_processing_topic", groupId = "group_id")
  public void consume(String orderProcessingMessage) {
    Gson gson = new Gson();
    OrderProcessingDto orderProcessing = gson.fromJson(orderProcessingMessage, OrderProcessingDto.class);
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
      inventoryManagerProducer.publish(new Gson().toJson(orderProcessing), INVENTORY_MANAGEMENT_TOPIC);
    } else {
      inventoryManagerProducer.publish(orderProcessing.getOrderId().toString(), ORDER_PROCESSING_ERROR_TOPIC);
    }
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