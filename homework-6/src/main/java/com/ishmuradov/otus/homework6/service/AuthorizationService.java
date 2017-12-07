package com.ishmuradov.otus.homework6.service;

import com.ishmuradov.otus.homework6.Account;
import com.ishmuradov.otus.homework6.util.AccessDeniedException;

/**
 * Pattern: Singleton
 * 
 * @author Ishmuradov
 *
 */
public class AuthorizationService {
  private static volatile AuthorizationService instance;
  AccountService accountService = AccountService.getInstance();
  
  public static enum Permission {
    WITHDRAW, DEPOSIT, CHECK_BALANCE
  }

  private AuthorizationService() {
  }

  public static AuthorizationService getInstance() {
    AuthorizationService localInstance = instance;
    if (localInstance == null) {
      synchronized (AuthorizationService.class) {
        localInstance = instance;
        if (localInstance == null) {
          instance = localInstance = new AuthorizationService();
        }
      }
    }
    return localInstance;
  }
  
  public void checkPermission(Account account, Permission permission) throws AccessDeniedException {
    boolean isAuthorized = account != null && permission != null
        && account.getPermissions().contains(permission);
    if (!isAuthorized) {
      throw new AccessDeniedException("You do not have the required permission: " + permission.toString());
    }
  }
  
}
