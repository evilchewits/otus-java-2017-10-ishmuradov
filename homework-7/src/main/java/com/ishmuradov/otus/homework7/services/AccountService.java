package com.ishmuradov.otus.homework7.services;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.ishmuradov.otus.homework7.Account;

/**
 * Pattern: Singleton
 * 
 * @author Ishmuradov
 *
 */
public class AccountService {
  private static volatile AccountService instance;
  private Map<BigInteger, Account> accountRegistry;

  private AccountService() {
    accountRegistry = new HashMap<>();
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
  
  public synchronized void registerAccounts(Account... accounts) {
    for (Account account : accounts) {
      accountRegistry.put(account.getId(), account);
    }
  }
  
  public synchronized Account getAccount(BigInteger id) {
    return accountRegistry.get(id);
  }

}
