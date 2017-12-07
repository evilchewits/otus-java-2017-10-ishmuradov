package com.ishmuradov.otus.homework6;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ishmuradov.otus.homework6.service.AuthenticationService;
import com.ishmuradov.otus.homework6.service.AuthorizationService;
import com.ishmuradov.otus.homework6.service.AuthorizationService.Permission;
import com.ishmuradov.otus.homework6.util.AuthenticationException;
import com.ishmuradov.otus.homework6.util.CellOverflowException;

public class ATM implements I_ATM {
  private Account currentAccount;
  private Map<Denomination, Integer> withdrawCells;
  private Map<Denomination, Integer> depositCells;
  private Map<Denomination, Integer> virtualCells;
  private AuthenticationService authenticationService;
  private AuthorizationService authorizationService;

  public ATM(Map<Denomination, Integer> withdrawCells, Map<Denomination, Integer> depositCells) {
    this.withdrawCells = withdrawCells;
    this.depositCells = depositCells;
    this.authenticationService = AuthenticationService.getInstance();
    this.authorizationService = AuthorizationService.getInstance();
  }

  @Override
  public void withdraw(long ammount) {
    checkPermission(currentAccount, Permission.WITHDRAW);
    resetVirtualCells();

    List<Denomination> banknotes = Arrays.asList(Denomination.values());
    Collections.sort(banknotes, Comparator.comparing(Denomination::getValue).reversed());
    long minValue = banknotes.get(banknotes.size() - 1).getValue();
    
    if (currentAccount.getBalance() < ammount) {
      System.out.println("Not enough money on the account");
      return;
    }
    
    if (ammount % minValue != 0) {
      System.out.println("The sum must be devisible by " + minValue);
      return;
    }
    
    long sum = 0;
    for (Denomination denomination : banknotes) {
      while (withdrawCells.get(denomination) - virtualCells.get(denomination) > 0
          && sum + denomination.getValue() <= ammount) {
        sum += denomination.getValue();
        virtualCells.put(denomination, virtualCells.get(denomination).intValue() + 1);
      }
    }
    
    if (sum != ammount) {
      System.out.println("The requested sum could not be issued");
      return;
    }

    virtualCells.forEach((k, v) -> withdrawCells.put(k, withdrawCells.get(k).intValue() - v));
    currentAccount.setBalance(currentAccount.getBalance() - sum);
  }
  
  @Override
  public void deposit(Map<Denomination, Integer> money) {
    checkPermission(currentAccount, Permission.DEPOSIT);
    resetVirtualCells();
    
    long sum = 0;
    for (Entry<Denomination, Integer> entry : money.entrySet()) {
      if (depositCells.get(entry.getKey()).intValue() + entry.getValue() > CELL_SIZE) {
        throw new CellOverflowException("Could not accept money: cell is full");
      }
      
      sum += entry.getKey().getValue() * entry.getValue();
      virtualCells.put(entry.getKey(), entry.getValue());
    }
    
    virtualCells.forEach((k, v) -> depositCells.put(k, depositCells.get(k).intValue() + v));
    currentAccount.setBalance(currentAccount.getBalance() + sum);
  }
  
  @Override
  public long getBalance() {
    checkPermission(currentAccount, Permission.CHECK_BALANCE);
   
    return currentAccount.getBalance();
  }
  
  @Override
  public void authenticate(BigInteger id, String pin) {
    if (currentAccount != null) {
      throw new AuthenticationException("ATM is already in use");
    }
    currentAccount = authenticationService.authenticate(id, pin);
  }
  
  @Override
  public void terminate() {
    currentAccount = null;
  }
  
  @Override
  public void checkPermission(Account account, Permission permission) {
    authorizationService.checkPermission(account, permission);
  }
  
  private void resetVirtualCells() {
    if (virtualCells == null) {
      virtualCells = new HashMap<>();
    }
    for (Denomination denomination : Denomination.values()) {
      virtualCells.put(denomination, 0);
    }
  }

  public Map<Denomination, Integer> getWithdrawCells() {
    return withdrawCells;
  }

  public void setWithdrawCells(Map<Denomination, Integer> withdrawCells) {
    this.withdrawCells = withdrawCells;
  }

  public Map<Denomination, Integer> getDepositCells() {
    return depositCells;
  }

  public void setDepositCells(Map<Denomination, Integer> depositCells) {
    this.depositCells = depositCells;
  }

}
