package com.unmsm.distributedsystems.reservation.service;

import com.unmsm.distributedsystems.reservation.model.api.OrderRequest;
import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void sendOrder(OrderRequest orderRequest);

    Optional<ArticleDto> findById(Integer code);

    List<ArticleDto> findAll();
}
