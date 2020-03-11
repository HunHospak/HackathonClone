package com.opal.brew.stock.services;

import com.opal.brew.stock.models.Hop;
import com.opal.brew.stock.models.Malt;
import com.opal.brew.stock.models.RawMaterial;
import com.opal.brew.stock.models.Yeast;
import com.opal.brew.stock.models.dtos.RawDto;
import com.opal.brew.stock.repositories.HopRepository;
import com.opal.brew.stock.repositories.MaltRepository;
import com.opal.brew.stock.repositories.YeastRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {

  private HopRepository hopRepository;
  private MaltRepository maltRepository;
  private YeastRepository yeastRepository;
  private CurrentMaterialStockService materialStockService;

  @Autowired
  public RawMaterialServiceImpl(HopRepository hopRepository, MaltRepository maltRepository,
      YeastRepository yeastRepository, CurrentMaterialStockService materialStockService) {
    this.hopRepository = hopRepository;
    this.maltRepository = maltRepository;
    this.yeastRepository = yeastRepository;
    this.materialStockService = materialStockService;
  }

  @Override
  public void save(RawDto rawDto) throws Exception {
    if (rawDto.getMaterial().equals("hop")) {
      hopRepository.save(new Hop(rawDto.getType(), rawDto.getQuantity(), rawDto.getTransportId()));
    } else if (rawDto.getMaterial().equals("yeast")) {
      yeastRepository
          .save(new Yeast(rawDto.getType(), rawDto.getQuantity(), rawDto.getTransportId()));
    } else if (rawDto.getMaterial().equals("malt")) {
      maltRepository
          .save(new Malt(rawDto.getType(), rawDto.getQuantity(), rawDto.getTransportId()));
    } else {
      throw new Exception("That is not a valid ingredient");
    }
    materialStockService.updateAmount(rawDto.getMaterial(), rawDto.getQuantity());
  }

  @Override
  public boolean validate(RawDto rawDto) {
    return rawDto.getTransportId() != null && !rawDto.getTransportId().equals(" ")
        && (rawDto.getMaterial().toLowerCase().equals("hop")
        || rawDto.getMaterial().toLowerCase().equals("yeast")
        || rawDto.getMaterial().toLowerCase().equals("malt"))
        && (rawDto.getQuantity() != 0);
  }

  @Override
  public List<RawMaterial> findAllMaterials() {
    List<RawMaterial> allRawMaterials = new ArrayList<>();
    hopRepository.findAll().stream().forEach(f->allRawMaterials.add(f));
    maltRepository.findAll().stream().forEach(f->allRawMaterials.add(f));
    yeastRepository.findAll().stream().forEach(f->allRawMaterials.add(f));
    return allRawMaterials;
  }
}
