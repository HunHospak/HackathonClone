package com.opal.brew.security.models;

import com.opal.brew.users.MyUser;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class ConfirmationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "token_id")
  private long tokenid;

  @Column(name = "confirmation_token")
  private String confirmationToken;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @OneToOne(targetEntity = MyUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "id")
  private MyUser myUser;

  public ConfirmationToken(MyUser user) {
    this.myUser = user;
    createdDate = new Date();
    confirmationToken = UUID.randomUUID().toString();
  }

  public ConfirmationToken() {
  }

  public long getTokenid() {
    return tokenid;
  }

  public void setTokenid(long tokenid) {
    this.tokenid = tokenid;
  }

  public String getConfirmationToken() {
    return confirmationToken;
  }

  public void setConfirmationToken(String confirmationToken) {
    this.confirmationToken = confirmationToken;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public MyUser getUser() {
    return myUser;
  }

  public void setUser(MyUser user) {
    this.myUser = user;
  }
}
