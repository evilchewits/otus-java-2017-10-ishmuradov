package com.ishmuradov.otus.homework6;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ishmuradov.otus.homework6.service.AccountService;
import com.ishmuradov.otus.homework6.service.AuthorizationService;

public class App {

  public static void main(String[] args) {
    
    AccountService accountService = AccountService.getInstance();
    AuthorizationService authorizationService = AuthorizationService.getInstance();
    
    // Create accounts and configure their access rights
    
    Account account1 = new Account(new BigInteger("123456"), Arrays.asList("0567", "8454"), 50000);
    Account account2 = new Account(new BigInteger("654321"), Arrays.asList("2469"), 1000);
    accountService.registerAccounts(account1, account2);
    authorizationService.grantPermissions(account2, Permission.WITHDRAW, Permission.DEPOSIT);
    authorizationService.grantPermissions(account1, Permission.WITHDRAW, Permission.DEPOSIT, Permission.CHECK_BALANCE);

    // Configure ATM
    
    List<Cell> withdrawCells = new ArrayList<>();
    withdrawCells.add(new Cell(Denomination.FIVE_THOUSAND, 1000));
    withdrawCells.add(new Cell(Denomination.TWO_THOUSAND,  1000));
    withdrawCells.add(new Cell(Denomination.THOUSAND,      1000));
    withdrawCells.add(new Cell(Denomination.FIVE_HUNDRED,  1000));
    withdrawCells.add(new Cell(Denomination.TWO_HUNDRED,   1000));
    withdrawCells.add(new Cell(Denomination.HUNDRED,       1000));
    withdrawCells.add(new Cell(Denomination.FIFTY,         1000));
    
    List<Cell> depositCells = new ArrayList<>();
    depositCells.add(new Cell(Denomination.FIVE_THOUSAND, 0));
    depositCells.add(new Cell(Denomination.TWO_THOUSAND,  0));
    depositCells.add(new Cell(Denomination.THOUSAND,      0));
    depositCells.add(new Cell(Denomination.FIVE_HUNDRED,  0));
    depositCells.add(new Cell(Denomination.TWO_HUNDRED,   0));
    depositCells.add(new Cell(Denomination.HUNDRED,       0));
    depositCells.add(new Cell(Denomination.FIFTY,         0));
    
    ATM atm = new ATM(withdrawCells, depositCells);
    
    // Interact with ATM
    
    System.out.println("Account ID: 123456");
    atm.authenticate(new BigInteger("123456"), "0567");
    System.out.println("Balance: " + atm.getBalance());
    System.out.println("Withdraw cells before operation: " + atm.getWithdrawCells());
    System.out.println("Withdraw sum: 20750");
    atm.withdraw(20750);
    System.out.println("Withdraw cells after operation: " + atm.getWithdrawCells());
    System.out.println("Balance: " + atm.getBalance());
    
    System.out.println("Deposit cells before operation: " + atm.getDepositCells());
    System.out.println("Deposit sum: 1100");
    Map<Denomination, Integer> money = new HashMap<>();
    money.put(Denomination.HUNDRED, 10);
    money.put(Denomination.FIFTY, 2);
    atm.deposit(money);
    System.out.println("Deposit cells after operation: " + atm.getDepositCells());
    System.out.println("Balance: " + atm.getBalance());
    atm.terminate();
    
    System.out.println("\n-----\n");
    
    System.out.println("Account ID: 654321");
    atm.authenticate(new BigInteger("654321"), "2469");
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    System.out.println("Withdraw cells before operation: " + atm.getWithdrawCells());
    System.out.println("Withdraw sum: 650");
    atm.withdraw(650);
    System.out.println("Withdraw cells after operation: " + atm.getWithdrawCells());
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    atm.terminate();
  }

}
