package com.opal.brew.stock.models;

import java.util.Calendar;
import javax.persistence.Entity;

@Entity
public class Yeast extends RawMaterial {

  public Yeast() {
    this.name="yeast";
  }

  public Yeast(String type, int quantity, String transportId) {
    super(type, quantity, transportId);
    this.name="yeast";
  }

  public Yeast(int quantity, String transportId) {
    super(quantity, transportId);
    this.name="yeast";
  }

  @Override
  public void setExpiryDate() {
    Calendar c = Calendar.getInstance();
    c.setTime(stockingDate);
    c.add(Calendar.MONTH, 1);
    this.expiryDate = c.getTime();
  }
}
