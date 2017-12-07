package com.ishmuradov.otus.homework6.service;

import java.math.BigInteger;
import java.util.Map;

import com.ishmuradov.otus.homework6.Account;

/**
 * Pattern: Singleton
 * 
 * @author Ishmuradov
 *
 */
public class AccountService {
  private static volatile AccountService instance;
  private Map<BigInteger, Account> accounts;

  private AccountService() {
  }

  public static AccountService getInstance() {
    AccountService localInstance = instance;
    if (localInstance == null) {
      synchronized (AccountService.class) {
        localInstance = instance;
        if (localInstance == null) {
          instance = localInstance = new AccountService();
        }
      }
    }
    return localInstance;
  }
  
  public void setAccounts(Map<BigInteger, Account> accounts) {
    this.accounts = accounts;
  }
  
  public Account getAccount(BigInteger id) {
    return accounts.get(id);
  }

}
