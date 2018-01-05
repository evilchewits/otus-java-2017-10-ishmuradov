package com.ishmuradov.otus.homework7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ishmuradov.otus.homework7.exceptions.AuthenticationException;
import com.ishmuradov.otus.homework7.exceptions.CellOverflowException;
import com.ishmuradov.otus.homework7.services.AuthenticationService;
import com.ishmuradov.otus.homework7.services.AuthorizationService;

/**
 * Patterns: Observer, Memento
 * 
 * @author Ishmuradov
 *
 */
public class ATM implements IATM, Observer {
  private Account currentAccount;
  private List<Cell> withdrawCells;
  private List<Cell> depositCells;
  private AuthenticationService authenticationService;
  private AuthorizationService authorizationService;
  CellStateMemento memento;
  
  
  public ATM(List<Cell> withdrawCells, List<Cell> depositCells) {
    this.withdrawCells = withdrawCells;
    this.depositCells = depositCells;
    this.authenticationService = AuthenticationService.getInstance();
    this.authorizationService = AuthorizationService.getInstance();
    saveToMemento();
  }

  @Override
  public void withdraw(long ammount) {
    checkPermission(currentAccount, Permission.WITHDRAW);
    resetReserved(withdrawCells);

    Collections.sort(withdrawCells,
        (c1, c2) -> Long.valueOf(c2.getDenomination().getValue()).compareTo(c1.getDenomination().getValue()));
    long minValue = withdrawCells.get(withdrawCells.size() - 1).getDenomination().getValue();
    
    if (currentAccount.getBalance() < ammount) {
      System.out.println("[WARNING] Not enough money on the account");
      return;
    }
    
    if (ammount % minValue != 0) {
      System.out.println("[WARNING] The sum must be devisible by " + minValue);
      return;
    }
    
    long sum = 0;
    for (Cell cell : withdrawCells) {
      while (cell.getAmmount() - cell.getReserved() > 0
          && sum + cell.getDenomination().getValue() <= ammount) {
        sum += cell.getDenomination().getValue();
        cell.setReserved(cell.getReserved() + 1);
      }
    }
    
    if (sum != ammount) {
      System.out.println("[WARNING] The requested sum could not be issued");
      return;
    }

    withdrawCells.forEach(c -> {
      c.setAmmount(c.getAmmount() - c.getReserved());
      c.setReserved(0);
    });
    currentAccount.setBalance(currentAccount.getBalance() - sum);
  }
  
  @Override
  public void deposit(Map<Denomination, Integer> money) {
    checkPermission(currentAccount, Permission.DEPOSIT);
    resetReserved(depositCells);
    
    long sum = 0;
    for (Entry<Denomination, Integer> entry : money.entrySet()) {
      for (Cell cell : depositCells) {
        if (!cell.getDenomination().equals(entry.getKey())) {
          continue;
        }
        
        if (cell.getAmmount() + entry.getValue() > Cell.SIZE) {
          throw new CellOverflowException("Could not accept money: cell is full");
        }
        
        sum += entry.getKey().getValue() * entry.getValue();
        cell.setReserved(entry.getValue());
      }

    }
    
    depositCells.forEach(c -> {
      c.setAmmount(c.getAmmount() + c.getReserved());
      c.setReserved(0);
    });
    currentAccount.setBalance(currentAccount.getBalance() + sum);
  }
  
  @Override
  public long getBalance() {
    checkPermission(currentAccount, Permission.CHECK_BALANCE);
   
    return currentAccount.getBalance();
  }
  
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
  
  public void checkPermission(Account account, Permission permission) {
    authorizationService.checkPermission(account, permission);
  }
  
  private void resetReserved(List<Cell> cells) {
    cells.forEach(c -> c.setReserved(0));
  }

  public List<Cell> getWithdrawCells() {
    return withdrawCells;
  }

  public void setWithdrawCells(List<Cell> withdrawCells) {
    this.withdrawCells = withdrawCells;
  }

  public List<Cell> getDepositCells() {
    return depositCells;
  }

  public void setDepositCells(List<Cell> depositCells) {
    this.depositCells = depositCells;
  }
  
  public long getATMBalance() {
    long withdrawCellsBalance = withdrawCells.stream().mapToLong(c -> c.getDenomination().getValue() * c.getAmmount())
        .sum();
    long depositCellsBalance = depositCells.stream().mapToLong(c -> c.getDenomination().getValue() * c.getAmmount())
        .sum();

    return withdrawCellsBalance + depositCellsBalance;
  }

  @Override
  public void notify(Event event) {
    event.execute();
  }

  public void saveToMemento() {
    this.memento = new CellStateMemento(withdrawCells, depositCells);
  }
  
  public void restoreFromMemento() {
    this.withdrawCells = this.memento.withdrawCells;
    this.depositCells = this.memento.depositCells;
  }

  public class CellStateMemento {
    private List<Cell> withdrawCells;
    private List<Cell> depositCells;
    
    public CellStateMemento(List<Cell> withdrawCells, List<Cell> depositCells) {
      try {
        this.withdrawCells = new ArrayList<>();
        for (Cell c : withdrawCells) {
          this.withdrawCells.add(c.clone());
        }
        this.depositCells = new ArrayList<>();
        for (Cell c : depositCells) {
          this.depositCells.add(c.clone());
        }
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
    }
  }
  
}
