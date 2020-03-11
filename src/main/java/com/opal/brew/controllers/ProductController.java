package com.opal.brew.controllers;

import com.opal.brew.delivery.models.dto.ProductDto;
import com.opal.brew.delivery.services.CurrentProductService;
import com.opal.brew.delivery.services.ProductService;
import javax.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

  private ProductService productService;

  private CurrentProductService currentProductService;

  @Autowired
  public ProductController(ProductService productService,
      CurrentProductService currentProductService) {
    this.productService = productService;
    this.currentProductService = currentProductService;
  }

  @PostMapping("/deliver")
  public ResponseEntity<?> deliverProductFromStock(@RequestBody ProductDto productDto) {
    if (productService.checkStockSize(productDto)) {
      productService.deliver(productDto);
      return new ResponseEntity(HttpStatus.ACCEPTED);
    } else {
      throw new BadRequestException("The current stock is too low to fulfil this request");
    }
  }

  @GetMapping("/products/list")
  public ResponseEntity<?> listAllCurrentProduct() {
    return ResponseEntity.status(HttpStatus.OK).body(currentProductService.findAll());
  }

  @GetMapping("/stats/products")
  public ResponseEntity<?> showAllProductsEverCreated(){
    return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
  }

}
