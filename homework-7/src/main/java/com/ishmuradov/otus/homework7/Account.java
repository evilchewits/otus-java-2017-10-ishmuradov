package com.ishmuradov.otus.homework7;

import java.math.BigInteger;
import java.util.List;

public class Account {

  private BigInteger id;
  private List<String> pins;
  private long balance;
  
  public Account(BigInteger id, List<String> pins, long balance) {
    super();
    this.id = id;
    this.pins = pins;
    this.balance = balance;
  }
  
  public BigInteger getId() {
    return id;
  }
  
  public void setId(BigInteger id) {
    this.id = id;
  }

  public List<String> getPins() {
    return pins;
  }

  public void setPins(List<String> pins) {
    this.pins = pins;
  }

  public long getBalance() {
    return balance;
  }
  
  public void setBalance(long balance) {
    this.balance = balance;
  }

}
