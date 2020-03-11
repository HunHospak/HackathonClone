package com.opal.brew.delivery.services;

import com.opal.brew.delivery.models.CurrentProduct;
import com.opal.brew.delivery.repository.CurrentProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentProductServiceImpl implements CurrentProductService {

  private CurrentProductRepository currentProductRepository;

  @Autowired
  CurrentProductServiceImpl(CurrentProductRepository currentProductRepository){
    this.currentProductRepository = currentProductRepository;
  }

  @Override
  public List<CurrentProduct> findAll() {
    return new ArrayList<>(currentProductRepository.findAll());
  }
}
