package com.ishmuradov.otus.homework6;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ishmuradov.otus.homework6.I_ATM.Denomination;
import com.ishmuradov.otus.homework6.service.AccountService;
import com.ishmuradov.otus.homework6.service.AuthorizationService.Permission;

public class App {

  public static void main(String[] args) {
    
    // Configure accounts
    
    Account account1 = new Account(new BigInteger("123456"), Arrays.asList("0567", "8454"), 50000,
        Arrays.asList(Permission.WITHDRAW, Permission.DEPOSIT, Permission.CHECK_BALANCE));
    Account account2 = new Account(new BigInteger("654321"), Arrays.asList("2469"), 1000,
        Arrays.asList(Permission.WITHDRAW, Permission.DEPOSIT));
    Map<BigInteger, Account> accounts = new HashMap<>();
    accounts.put(account1.getId(), account1);
    accounts.put(account2.getId(), account2);
    AccountService accountService = AccountService.getInstance();
    accountService.setAccounts(accounts);
    
    
    // Configure ATM
    
    Map<Denomination, Integer> withdrawCells = new HashMap<>();
    withdrawCells.put(Denomination.FIFTY,         1000);
    withdrawCells.put(Denomination.HUNDRED,       1000);
    withdrawCells.put(Denomination.TWO_HUNDRED,   1000);
    withdrawCells.put(Denomination.FIVE_HUNDRED,  1000);
    withdrawCells.put(Denomination.THOUSAND,      1000);
    withdrawCells.put(Denomination.TWO_THOUSAND,  1000);
    withdrawCells.put(Denomination.FIVE_THOUSAND, 1000);
    
    Map<Denomination, Integer> depositCells = new HashMap<>();
    depositCells.put(Denomination.FIFTY,         0);
    depositCells.put(Denomination.HUNDRED,       0);
    depositCells.put(Denomination.TWO_HUNDRED,   0);
    depositCells.put(Denomination.FIVE_HUNDRED,  0);
    depositCells.put(Denomination.THOUSAND,      0);
    depositCells.put(Denomination.TWO_THOUSAND,  0);
    depositCells.put(Denomination.FIVE_THOUSAND, 0);
    
    ATM atm = new ATM(withdrawCells, depositCells);
    
    
    // Interact with ATM
    
    System.out.println("Account: 123456");
    System.out.println("Withdraw sum: 20750");
    atm.authenticate(new BigInteger("123456"), "0567");
    atm.withdraw(20750);
    System.out.println("Withdraw cells after operation: " + atm.getWithdrawCells());
    System.out.println("Balance: " + atm.getBalance());
    
    System.out.println("Deposit sum: 1100");
    Map<Denomination, Integer> money = new HashMap<>();
    money.put(Denomination.HUNDRED, 10);
    money.put(Denomination.FIFTY, 2);
    atm.deposit(money);
    System.out.println("Deposit cells after operation: " + atm.getDepositCells());
    System.out.println("Balance: " + atm.getBalance());
    atm.terminate();
    
    System.out.println("\n-----\n");
    
    System.out.println("Account: 654321");
    System.out.println("Withdraw sum: 650");
    atm.authenticate(new BigInteger("654321"), "2469");
    atm.withdraw(650);
    System.out.println("Withdraw cells after operation: " + atm.getWithdrawCells());
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    atm.terminate();
  }

}
