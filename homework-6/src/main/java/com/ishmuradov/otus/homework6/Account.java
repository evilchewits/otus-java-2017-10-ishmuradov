package com.ishmuradov.otus.homework6;

import java.math.BigInteger;
import java.util.List;

import com.ishmuradov.otus.homework6.service.AuthorizationService.Permission;

public class Account {

  private BigInteger id;
  private List<String> pins;
  private long balance;
  private List<Permission> permissions;
  
  public Account(BigInteger id, List<String> pins, long balance, List<Permission> permissions) {
    super();
    this.id = id;
    this.pins = pins;
    this.balance = balance;
    this.permissions = permissions;
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

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

}
