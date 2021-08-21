package com.unmsm.distributedsystems.reservation.expose.web;

import com.unmsm.distributedsystems.reservation.model.api.OrderRequest;
import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;
import com.unmsm.distributedsystems.reservation.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(orderService.findById(id).get());
    }

    @PostMapping
    public void sendOrder(@RequestBody OrderRequest orderRequest) {
        orderService.sendOrder(orderRequest);
    }

}
