package com.opal.brew.stock.services;

import com.opal.brew.stock.models.CurrentMaterialStock;
import com.opal.brew.stock.models.RawMaterial;
import java.util.List;

public interface CurrentMaterialStockService {

  void updateAmount(String material, int amount);

  List<CurrentMaterialStock> findAll();
}
