package com.opal.brew.delivery.services;

import com.opal.brew.delivery.models.CurrentProduct;
import com.opal.brew.delivery.models.SoldProduct;
import com.opal.brew.delivery.models.dto.ProductDto;
import com.opal.brew.delivery.repository.CurrentProductRepository;
import com.opal.brew.delivery.repository.SoldProductRepository;
import com.opal.brew.production.models.InProgressProduct;
import com.opal.brew.delivery.models.Product;
import com.opal.brew.delivery.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private CurrentProductRepository currentProductRepository;
  private SoldProductRepository soldProductRepository;
  private ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(CurrentProductRepository currentProductRepository,
      ProductRepository productRepository, SoldProductRepository soldProductRepository) {
    this.currentProductRepository = currentProductRepository;
    this.productRepository = productRepository;
    this.soldProductRepository = soldProductRepository;
  }

  @Override
  public void save(Product product) {
    productRepository.save(product);
    if (currentProductRepository.findAllName().contains(product.getName())) {
      currentProductRepository.addAmount(product.getName(), product.getQuantity());
    } else {
      currentProductRepository.save(new CurrentProduct(product.getName(), product.getType(), product.getQuantity()));
    }
  }

  @Override
  public void deliver(ProductDto productDto) {
    currentProductRepository.updateAmount(productDto.getProductName(), productDto.getQuantity());
    soldProductRepository.save(new SoldProduct(productDto.getProductName(), productDto.getQuantity(), productDto.getType(), productDto.getTransportId()));
  }

  @Override
  public boolean checkStockSize(ProductDto productDto) {
    int quantityNeeded = productDto.getQuantity();
    String productNeeded = productDto.getProductName();
    int quantityOnStock = currentProductRepository.findByProductName(productNeeded).get().getQuantity();

    return quantityOnStock >= quantityNeeded;
  }

  @Override
  public Product createProduct(InProgressProduct inProgressProduct) {
    Product product = new Product();
    product.setName(inProgressProduct.getRecipe().getRecipeName());
    product.setQuantity(inProgressProduct.getReservedHop() + inProgressProduct.getReservedMalt() + inProgressProduct.getReservedYeast());
    product.setInProgressProduct(inProgressProduct);
    return product;
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
