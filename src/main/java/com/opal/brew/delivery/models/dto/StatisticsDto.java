package com.opal.brew.delivery.models.dto;

import java.util.Date;

public class StatisticsDto {

  private String name;
  private Date before;
  private Date after;

  public StatisticsDto() {
  }

  public String getName() {
    return name;
  }

  public Date getBefore() {
    return before;
  }

  public Date getAfter() {
    return after;
  }
}
