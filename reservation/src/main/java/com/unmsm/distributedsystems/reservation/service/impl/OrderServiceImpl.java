package com.unmsm.distributedsystems.reservation.service.impl;

import com.google.gson.Gson;
import com.unmsm.distributedsystems.reservation.broker.OrderProducer;
import com.unmsm.distributedsystems.reservation.dao.OrderDao;
import com.unmsm.distributedsystems.reservation.model.api.Article;
import com.unmsm.distributedsystems.reservation.model.api.OrderRequest;
import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;
import com.unmsm.distributedsystems.reservation.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;
    private final OrderDao dao;

    @Override
    public void sendOrder(OrderRequest orderRequest) {
        updateStockAndSendToTopic(orderRequest);
    }

    @Override
    public Optional<ArticleDto> findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<ArticleDto> findAll() {
        return dao.findAll();
    }

    private void updateStockAndSendToTopic(OrderRequest orderRequest) {
        Integer actualStock = dao.findById(orderRequest.getArticle().getId()).get().getStock();
        Integer requiredQuantity = orderRequest.getArticle().getQuantity();
        Article article = orderRequest.getArticle();

        if (requiredQuantity <= actualStock) {
            dao.updateStock(article.getQuantity(), article.getId());
            orderProducer.publish(new Gson().toJson(orderRequest));
            System.out.println("JSON-ORDER-REQUEST: \n" + new Gson().toJson(orderRequest));
        }
    }

}
