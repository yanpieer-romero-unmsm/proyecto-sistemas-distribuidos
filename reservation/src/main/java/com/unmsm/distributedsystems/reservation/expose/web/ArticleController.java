package com.unmsm.distributedsystems.reservation.expose.web;

import com.unmsm.distributedsystems.reservation.model.dto.ArticleDto;
import com.unmsm.distributedsystems.reservation.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

  private ArticleService service;

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ArticleDto>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }
}
