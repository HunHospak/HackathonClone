package com.opal.brew.stock.models;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class RawMaterial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;
  protected String type;
  protected Date stockingDate;
  protected Date expiryDate;
  protected int quantity;
  protected String transportId;
  protected String name;

  public RawMaterial() {
    this.stockingDate = new Date();
    setExpiryDate();
  }

  public RawMaterial(String type, int quantity, String transportId) {
    this.type = type;
    this.stockingDate = new Date();
    this.quantity = quantity;
    this.transportId = transportId;
    setExpiryDate();
  }

  public RawMaterial(int quantity, String transportId) {
    this.stockingDate = new Date();
    this.quantity = quantity;
    this.transportId = transportId;
    setExpiryDate();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getStockingDate() {
    return stockingDate;
  }

  public void setStockingDate() {
    stockingDate = new Date();
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public abstract void setExpiryDate();

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getTransportId() {
    return transportId;
  }

  public void setTransportId(String transportId) {
    this.transportId = transportId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
