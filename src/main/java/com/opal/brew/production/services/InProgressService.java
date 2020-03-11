package com.opal.brew.production.services;

import com.opal.brew.production.models.InProgressProduct;
import java.util.List;

public interface InProgressService {

  void save(InProgressProduct inProgressProduct);

  InProgressProduct findById(int id);

  void approve(InProgressProduct inProgressProduct);

  void cancel(InProgressProduct inProgressProduct);

  boolean isValid(InProgressProduct inProgressProduct);

  InProgressProduct create(String recipeName);

  List<InProgressProduct> findAllInProgress();
}
