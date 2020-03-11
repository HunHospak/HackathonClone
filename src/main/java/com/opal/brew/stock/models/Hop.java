package com.opal.brew.stock.models;

import javax.persistence.Entity;
import java.util.Calendar;

@Entity
public class Hop extends RawMaterial {

  public Hop() {
    this.name = "hop";
  }

  public Hop(String type, int quantity, String transportId) {
    super(type, quantity, transportId);
    this.name = "hop";
  }

  public Hop(int quantity, String transportId) {
    super(quantity, transportId);
    this.name = "hop";
  }

  @Override
  public void setExpiryDate() {
    Calendar c = Calendar.getInstance();
    c.setTime(stockingDate);
    c.add(Calendar.MONTH, 5);
    this.expiryDate = c.getTime();
  }
}
