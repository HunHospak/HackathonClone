package com.opal.brew.delivery.models.dto;

public class ProductDto {

  private String productName;
  private String type;
  private int quantity;
  private String transportId;


  public ProductDto() {
  }

  public String getTransportId() {
    return transportId;
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
