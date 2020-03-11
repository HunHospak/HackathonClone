package com.opal.brew.production.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opal.brew.production.models.InProgressProduct;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String recipeName;
  private String maltType;
  private int maltQuantity;
  private String hopType;
  private int hopQuantity;
  private String yeastType;
  private int yeastQuantity;
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
  private List<InProgressProduct> progressProducts = new ArrayList<>();

  public Recipe() {
  }

  public Recipe(String recipeName, int maltQuantity, int hopQuantity, int yeastQuantity) {
    this.recipeName = recipeName;
    this.maltQuantity = maltQuantity;
    this.hopQuantity = hopQuantity;
    this.yeastQuantity = yeastQuantity;
  }

  public Recipe(String recipeName, String maltType, int maltQuantity, String hopType,
      int hopQuantity,
      String yeastType, int yeastQuantity) {
    this.recipeName = recipeName;
    this.maltType = maltType;
    this.maltQuantity = maltQuantity;
    this.hopType = hopType;
    this.hopQuantity = hopQuantity;
    this.yeastType = yeastType;
    this.yeastQuantity = yeastQuantity;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipe) {
    this.recipeName = recipe;
  }

  public String getMaltType() {
    return maltType;
  }

  public void setMaltType(String maltType) {
    this.maltType = maltType;
  }

  public int getMaltQuantity() {
    return maltQuantity;
  }

  public void setMaltQuantity(int maltQuantity) {
    this.maltQuantity = maltQuantity;
  }

  public String getHopType() {
    return hopType;
  }

  public void setHopType(String hopType) {
    this.hopType = hopType;
  }

  public int getHopQuantity() {
    return hopQuantity;
  }

  public void setHopQuantity(int hopQuantity) {
    this.hopQuantity = hopQuantity;
  }

  public String getYeastType() {
    return yeastType;
  }

  public void setYeastType(String yeastType) {
    this.yeastType = yeastType;
  }

  public int getYeastQuantity() {
    return yeastQuantity;
  }

  public void setYeastQuantity(int yeastQuantity) {
    this.yeastQuantity = yeastQuantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<InProgressProduct> getProgressProducts() {
    return progressProducts;
  }

  public void addProgressProducts(InProgressProduct progressProduct) {
    progressProduct.setRecipe(this);
    progressProducts.add(progressProduct);
  }
  public void dismissProgress(InProgressProduct product){
    this.progressProducts.remove(product);
  }
}
