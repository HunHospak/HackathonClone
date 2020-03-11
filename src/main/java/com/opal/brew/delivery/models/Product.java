package com.opal.brew.delivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opal.brew.production.models.InProgressProduct;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String type;
  private Date stockingDate;
  private Date expiryDate;
  private int quantity;
  @JsonIgnore
  @OneToOne
  private InProgressProduct inProgressProduct;

  public Product() {
    stockingDate = new Date();
    setExpiryDate();
  }

  public Product(String type, int quantity) {
    this.type = type;
    this.stockingDate = new Date();
    setExpiryDate();
    this.quantity = quantity;
  }

  public Product(int quantity) {
    this.stockingDate = new Date();
    setExpiryDate();
    this.quantity = quantity;
  }

  public Product(String name, String type, int quantity,
      String productionId) {
    this.name = name;
    this.type = type;
    this.stockingDate = new Date();
    setExpiryDate();
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public void setStockingDate(Date stockingDate) {
    this.stockingDate = stockingDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate() {
    Calendar c = Calendar.getInstance();
    c.setTime(stockingDate);
    c.add(Calendar.YEAR, 1);
    this.expiryDate = c.getTime();
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public InProgressProduct getInProgressProduct() {
    return inProgressProduct;
  }

  public void setInProgressProduct(InProgressProduct inProgressProduct) {
    inProgressProduct.setProduct(this);
    this.inProgressProduct = inProgressProduct;
  }
}
