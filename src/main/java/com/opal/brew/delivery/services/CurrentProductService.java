package com.opal.brew.delivery.services;

import com.opal.brew.delivery.models.CurrentProduct;

import java.util.List;

public interface CurrentProductService {

  List<CurrentProduct> findAll();
}
