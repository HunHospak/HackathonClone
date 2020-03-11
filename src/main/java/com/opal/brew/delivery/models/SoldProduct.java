package com.opal.brew.delivery.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SoldProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String productName;
  private Date sellingDate;
  private String type;
  private int quantity;
  private String transportId;

  public SoldProduct() {
    sellingDate = new Date();
  }

  public SoldProduct(String productName, int quantity, String type, String transportId) {
    sellingDate = new Date();
    this.productName = productName;
    this.quantity = quantity;
    this.type = type;
    this.transportId = transportId;
  }

  public String getTransportId() {
    return transportId;
  }

  public void setTransportId(String transportId) {
    this.transportId = transportId;
  }

  public Date getSellingDate() {
    return sellingDate;
  }

  public void setSellingDate(Date sellingDate) {
    this.sellingDate = sellingDate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
