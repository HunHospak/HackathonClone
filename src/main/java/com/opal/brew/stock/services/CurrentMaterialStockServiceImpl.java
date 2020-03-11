package com.opal.brew.stock.services;

import com.opal.brew.stock.models.CurrentMaterialStock;
import com.opal.brew.stock.repositories.CurrentMaterialStockRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentMaterialStockServiceImpl implements CurrentMaterialStockService {

  private CurrentMaterialStockRepository stockRepository;

  @Autowired
  public CurrentMaterialStockServiceImpl(CurrentMaterialStockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @Override
  public void updateAmount(String material, int amount) {
    if (stockRepository.findAllType().contains(material)) {
      stockRepository.updateAmount(material, amount);
    } else {
      stockRepository.save(new CurrentMaterialStock(material, amount, 0));
    }
  }

  @Override
  public List<CurrentMaterialStock> findAll() {
    return stockRepository.findAll();
  }

}