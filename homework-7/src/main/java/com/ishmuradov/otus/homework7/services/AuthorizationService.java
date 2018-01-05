package com.ishmuradov.otus.homework7.services;

import java.math.BigInteger;
import java.util.Arrays;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.ishmuradov.otus.homework7.Account;
import com.ishmuradov.otus.homework7.Permission;
import com.ishmuradov.otus.homework7.exceptions.AccessDeniedException;

/**
 * Pattern: Singleton
 * 
 * @author Ishmuradov
 *
 */
public class AuthorizationService {
  private static volatile AuthorizationService instance;
  AccountService accountService = AccountService.getInstance();
  ListMultimap<BigInteger, Permission> permissionMap;
  
  private AuthorizationService() {
    permissionMap = ArrayListMultimap.create();
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
  
  public synchronized void checkPermission(Account account, Permission permission) throws AccessDeniedException {
    Account registeredAccount = accountService.getAccount(account.getId());
    boolean isAuthorized = registeredAccount != null && permission != null
        && permissionMap.containsEntry(registeredAccount.getId(), permission);
    if (!isAuthorized) {
      throw new AccessDeniedException("You do not have the required permission: " + permission.toString());
    }
  }

  public synchronized boolean grantPermissions(Account account, Permission... permissions) {
    if (accountService.getAccount(account.getId()) == null) {
      return false;
    }
    return permissionMap.putAll(account.getId(), Arrays.asList(permissions));
  }
  
}
