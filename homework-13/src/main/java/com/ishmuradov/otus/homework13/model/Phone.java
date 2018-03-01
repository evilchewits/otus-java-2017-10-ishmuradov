package com.ishmuradov.otus.homework13.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "PHONE")
public class Phone extends Entity {

  @Column(name = "NUMBER")
  private String number;

  @ManyToOne
  private User user;

  public Phone() {
    super();
  }

  public Phone(String number) {
    super();
    this.number = number;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Phone [number=" + number + "]";
  }

}
