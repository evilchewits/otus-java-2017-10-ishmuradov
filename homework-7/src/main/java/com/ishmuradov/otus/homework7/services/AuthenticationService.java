package com.ishmuradov.otus.homework7.services;

import java.math.BigInteger;

import com.ishmuradov.otus.homework7.Account;
import com.ishmuradov.otus.homework7.exceptions.AuthenticationException;

/**
 * Pattern: Singleton
 * 
 * @author Ishmuradov
 *
 */
public class AuthenticationService {
  private static volatile AuthenticationService instance;
  AccountService accountService = AccountService.getInstance();

  private AuthenticationService() {
  }

  public static AuthenticationService getInstance() {
    AuthenticationService localInstance = instance;
    if (localInstance == null) {
      synchronized (AuthenticationService.class) {
        localInstance = instance;
        if (localInstance == null) {
          instance = localInstance = new AuthenticationService();
        }
      }
    }
    return localInstance;
  }

  public Account authenticate(BigInteger id, String pin) throws AuthenticationException {
    Account account = accountService.getAccount(id);
    boolean isAuthenticated = id != null && pin != null && account != null && account.getPins().contains(pin);

    if (!isAuthenticated) {
      throw new AuthenticationException("Failed to authenticate: " + id.toString());
    }

    return account;
  }
}
