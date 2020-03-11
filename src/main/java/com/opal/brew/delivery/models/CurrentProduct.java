package com.opal.brew.delivery.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CurrentProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String productName;
  private String type;
  private int quantity;

  public CurrentProduct() {
  }

  public CurrentProduct(String name, String type, int quantity) {
    this.productName = name;
    this.type = type;
    this.quantity = quantity;
  }

  public long getId() {
    return id;
  }

  public String getProductName() {
    return productName;
  }

  public String getType() {
    return type;
  }

  public int getQuantity() {
    return quantity;
  }
}
