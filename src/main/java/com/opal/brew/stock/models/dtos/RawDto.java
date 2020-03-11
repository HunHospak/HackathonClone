package com.opal.brew.stock.models.dtos;

public class RawDto {

  private String material;
  private String type;
  private int quantity;
  private String transportId;

  public RawDto() {
    this.type = "basic";
  }

  public RawDto(String material, String type, int quantity, String transportId) {
    this.material = material;
    this.type = type;
    this.quantity = quantity;
    this.transportId = transportId;
  }

  public String getMaterial() {
    return material;
  }

  public String getType() {
    return type;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getTransportId() {
    return transportId;
  }
}
