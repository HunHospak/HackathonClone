package com.opal.brew.delivery.services;

import com.opal.brew.production.models.InProgressProduct;
import com.opal.brew.delivery.models.Product;
import com.opal.brew.delivery.models.dto.ProductDto;
import java.util.List;

public interface ProductService {

  void save(Product product);

  void deliver(ProductDto productDto);

  boolean checkStockSize(ProductDto productDto);

  Product createProduct(InProgressProduct inProgressProduct);

  List<Product> findAll();
}
