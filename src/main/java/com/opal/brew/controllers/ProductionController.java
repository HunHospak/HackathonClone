package com.opal.brew.controllers;

import com.opal.brew.stock.models.dtos.InProgressProductDto;
import java.util.ArrayList;
import com.opal.brew.production.models.InProgressProduct;
import com.opal.brew.production.services.InProgressService;
import com.opal.brew.delivery.services.ProductService;

import java.util.List;
import javax.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductionController {

  private InProgressService inProgressService;
  private ProductService productService;

  @Autowired
  public ProductionController(InProgressService inProgressService,
      ProductService productService) {
    this.inProgressService = inProgressService;
    this.productService = productService;
  }

  @PostMapping("/produce")
  public ResponseEntity startProduce(@RequestParam(name = "name") String recipeName) {
    inProgressService.save(inProgressService.create(recipeName));
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @GetMapping("/inprogress/list")
  public ResponseEntity listAllInProgress() {
    List<InProgressProduct> products = inProgressService.findAllInProgress();
    List<InProgressProductDto> productDtos=new ArrayList<>();
    products.forEach(i->productDtos.add(new InProgressProductDto(i)));
    if (products.isEmpty()) {
      throw new BadRequestException("No inprogress productions!");
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(productDtos);
    }
  }

  @PutMapping("/approve/{id}")
  public ResponseEntity approveProgress(@PathVariable int id) {
    InProgressProduct productToApprove = inProgressService.findById(id);
    if (productToApprove == null) {
      throw new BadRequestException("No production found!");
    } else {
      inProgressService.approve(productToApprove);
      productService.save(productService.createProduct(productToApprove));
      return new ResponseEntity(HttpStatus.OK);
    }
  }

  @PutMapping("/cancel/{id}")
  public ResponseEntity cancelProgress(@PathVariable int id) {
    InProgressProduct productToCancel = inProgressService.findById(id);
    if (productToCancel == null) {
      throw new BadRequestException("No production found!");
    } else {
      inProgressService.cancel(productToCancel);
      return new ResponseEntity(HttpStatus.OK);
    }
  }
}
