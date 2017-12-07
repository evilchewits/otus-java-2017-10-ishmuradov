package com.ishmuradov.otus.homework6;

import java.math.BigInteger;
import java.util.Map;

import com.ishmuradov.otus.homework6.service.AuthorizationService.Permission;

public interface I_ATM {
  static final int CELL_SIZE = 10_000;

  static enum Denomination {
    FIFTY         (50),
    HUNDRED       (100),
    TWO_HUNDRED   (200),
    FIVE_HUNDRED  (500),
    THOUSAND      (1000),
    TWO_THOUSAND  (2000),
    FIVE_THOUSAND (5000);
    
    private final long value;

    private Denomination(long value) {
      this.value = value;
    }
    
    public long getValue() {
      return value;
    }
  }   
  
  void withdraw(long ammount);

  void deposit(Map<Denomination, Integer> money);

  long getBalance();
  
  void authenticate(BigInteger id, String pin);
  
  void checkPermission(Account account, Permission permission);
  
  void terminate();
}
