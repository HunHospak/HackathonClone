package com.opal.brew.controllers;

import com.opal.brew.delivery.models.dto.StatisticsDto;
import com.opal.brew.delivery.repository.CurrentProductRepository;
import com.opal.brew.delivery.repository.ProductRepository;
import com.opal.brew.delivery.repository.SoldProductRepository;
import com.opal.brew.production.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StatisticsController {

  private SoldProductRepository soldProductRepository;
  private ProductRepository productRepository;
  private RecipeService recipeService;
  private CurrentProductRepository currentProductRepository;

  @Autowired
  StatisticsController(SoldProductRepository soldProductRepository, ProductRepository productRepository, RecipeService recipeService, CurrentProductRepository currentProductRepository) {
    this.soldProductRepository = soldProductRepository;
    this.productRepository = productRepository;
    this.recipeService = recipeService;
    this.currentProductRepository = currentProductRepository;
  }

  @GetMapping("/stats/{stat}")
  public ResponseEntity<?> shwStats(@PathVariable(name = "stat") String stat, @RequestBody StatisticsDto statisticsDto) {
    switch (stat) {
      case "expired":
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.showExpiredProducts());
      case "sold":
        if (statisticsDto.getName()!= null && currentProductRepository.findByProductName(statisticsDto.getName()).isPresent() && !soldProductRepository.findAllByProductName(statisticsDto.getName()).isEmpty()) {
          return ResponseEntity.status(HttpStatus.OK).body(soldProductRepository.soldOrderByProductName(statisticsDto.getName()));
        }
        break;
      case "soldbetween":
        if (statisticsDto.getBefore() != null && statisticsDto.getAfter() != null)
        return ResponseEntity.status(HttpStatus.OK).body(soldProductRepository.findAllBySellingDateBetween(statisticsDto.getBefore(), statisticsDto.getAfter()));
      case "soldbetweencount":
        return ResponseEntity.status(HttpStatus.OK).body(soldProductRepository.soldBetweenCount(statisticsDto.getBefore(), statisticsDto.getAfter()));
    }
    throw new BadRequestException("Invalid Request");
  }

}
