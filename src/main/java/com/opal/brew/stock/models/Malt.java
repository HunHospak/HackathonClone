package com.opal.brew.stock.models;

import javax.persistence.Entity;
import java.util.Calendar;

@Entity
public class Malt extends RawMaterial {

  public Malt() {
    this.name="malt";
  }

  public Malt(String type, int quantity, String transportId) {
    super(type, quantity, transportId);
    this.name="malt";
  }

  public Malt(int quantity, String transportId) {
    super(quantity, transportId);
    this.name="malt";
  }

  @Override
  public void setExpiryDate() {
    Calendar c = Calendar.getInstance();
    c.setTime(stockingDate);
    c.add(Calendar.MONTH, 3);
    this.expiryDate = c.getTime();
  }
}
