package com.opal.brew.production.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.opal.brew.delivery.models.Product;
import javax.persistence.PreRemove;

@Entity
public class InProgressProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Date startingDate;
  private Date finishDate;
  @JsonIgnore
  @ManyToOne
  private Recipe recipe;
  private boolean isApproved;
  @JsonIgnore
  @OneToOne(mappedBy = "inProgressProduct")
  private Product product;
  
  public InProgressProduct() {
    this.startingDate = new Date();
    this.isApproved = false;
  }

  @PreRemove
  public void dismissRelations(){
    this.recipe.dismissProgress(this);
    this.setRecipe(null);
  }

  public int getId() {
    return id;
  }

  public Date getStartingDate() {
    return startingDate;
  }

  public Date getFinishDate() {
    return finishDate;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public boolean isApproved() {
    return isApproved;
  }

  private void setFinishDate() {
    finishDate = new Date();
  }

  public void setApproved(boolean approved) {
    isApproved = approved;
    setFinishDate();
  }

  public int getReservedHop() {
    return recipe.getHopQuantity();
  }

  public int getReservedMalt() {
    return recipe.getMaltQuantity();
  }

  public int getReservedYeast() {
    return recipe.getYeastQuantity();
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
