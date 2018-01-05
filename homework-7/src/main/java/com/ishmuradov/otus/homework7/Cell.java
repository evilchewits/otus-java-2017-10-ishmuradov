package com.ishmuradov.otus.homework7;

public class Cell implements Cloneable {
  public static final int SIZE = 10_000;
  
  private Denomination denomination;
  private int ammount;
  private int reserved;
  
  public Cell(Denomination denomination, int ammount) {
    super();
    this.denomination = denomination;
    this.ammount = ammount;
    this.reserved = 0;
  }

  public Denomination getDenomination() {
    return denomination;
  }
  
  public void setDenomination(Denomination denomination) {
    this.denomination = denomination;
  }
  
  public int getAmmount() {
    return ammount;
  }
  
  public void setAmmount(int ammount) {
    this.ammount = ammount;
  }

  public int getReserved() {
    return reserved;
  }

  public void setReserved(int reserved) {
    this.reserved = reserved;
  }
  
  @Override
  public String toString() {
    return String.join("", "[", getDenomination() + "]: ", String.valueOf(getAmmount()), " ");
  }
  
  @Override
  public Cell clone() throws CloneNotSupportedException {
    Cell obj = (Cell) super.clone();
    obj.denomination = this.denomination;
    obj.ammount = this.ammount;
    obj.reserved = this.reserved;
    return obj;
  }
  
}
