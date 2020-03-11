package com.opal.brew.stock.models.dtos;

import com.opal.brew.production.models.InProgressProduct;

public class InProgressProductDto {

  private int id;
  private String name;
  private String startingDate;

  public InProgressProductDto() {
  }

  public InProgressProductDto(int id, String name, String startingDate) {
    this.id = id;
    this.name = name;
    this.startingDate = startingDate;
  }

  public InProgressProductDto(InProgressProduct product) {
    this.id = product.getId();
    this.name = product.getRecipe().getRecipeName();
    this.startingDate = product.getStartingDate().toString();
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

  public String getStartingDate() {
    return startingDate;
  }

  public void setStartingDate(String startingDate) {
    this.startingDate = startingDate;
  }
}
