package com.opal.brew.controllers;

import com.opal.brew.stock.models.dtos.RawDto;
import com.opal.brew.stock.services.CurrentMaterialStockService;
import com.opal.brew.stock.services.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RawMaterialController {

  private RawMaterialService rawMaterialService;
  private CurrentMaterialStockService currentMaterialStockService;

  @Autowired
  public RawMaterialController(RawMaterialService rawMaterialService,
      CurrentMaterialStockService currentMaterialStockService) {
    this.rawMaterialService = rawMaterialService;
    this.currentMaterialStockService = currentMaterialStockService;
  }

  @PostMapping("/stock")
  public ResponseEntity<?> saveNewIncomingMaterials(@RequestBody RawDto rawDto) throws Exception{
    if (rawMaterialService.validate(rawDto)){
      rawMaterialService.save(rawDto);
      return new ResponseEntity(HttpStatus.CREATED);
    } else{
      throw new Exception("Please provide a valid request");
    }
  }

  @GetMapping("/stats/materials")
  public ResponseEntity<?> getAllRawMaterials() throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(rawMaterialService.findAllMaterials());
  }

  @GetMapping("/material/current/list")
  public ResponseEntity getCurrentMaterials() {
    return ResponseEntity.status(HttpStatus.OK).body(currentMaterialStockService.findAll());
  }
}
