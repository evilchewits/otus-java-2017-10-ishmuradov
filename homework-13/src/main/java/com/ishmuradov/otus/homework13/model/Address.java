package com.ishmuradov.otus.homework13.model;

import javax.persistence.Column;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "ADDRESS")
public class Address extends Entity {

  @Column(name = "STREET")
  private String street;
  
  @Column(name = "BUILDING")
  private int building;

  public Address() {
    super();
  }

  public Address(String street, int building) {
    super();
    this.street = street;
    this.building = building;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getBuilding() {
    return building;
  }

  public void setBuilding(int building) {
    this.building = building;
  }

  @Override
  public String toString() {
    return "Address [street=" + street + ", building=" + building + "]";
  }

}
