package com.opal.brew.stock.services;

import com.opal.brew.stock.models.RawMaterial;
import com.opal.brew.stock.models.dtos.RawDto;
import java.util.List;

public interface RawMaterialService {
  void save(RawDto rawDto) throws Exception;
  boolean validate(RawDto rawDto);
  List<RawMaterial> findAllMaterials();
}
