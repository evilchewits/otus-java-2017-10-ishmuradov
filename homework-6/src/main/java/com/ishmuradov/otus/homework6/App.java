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

    // Configure ATMs
    
    List<Cell> withdrawCells1 = new ArrayList<>();
    withdrawCells1.add(new Cell(Denomination.FIVE_THOUSAND, 1000));
    withdrawCells1.add(new Cell(Denomination.TWO_THOUSAND,  1000));
    withdrawCells1.add(new Cell(Denomination.THOUSAND,      1000));
    withdrawCells1.add(new Cell(Denomination.FIVE_HUNDRED,  1000));
    withdrawCells1.add(new Cell(Denomination.TWO_HUNDRED,   1000));
    withdrawCells1.add(new Cell(Denomination.HUNDRED,       1000));
    withdrawCells1.add(new Cell(Denomination.FIFTY,         1000));
    
    List<Cell> withdrawCells2 = new ArrayList<>();
    withdrawCells2.add(new Cell(Denomination.FIVE_THOUSAND, 500));
    withdrawCells2.add(new Cell(Denomination.TWO_THOUSAND,  500));
    withdrawCells2.add(new Cell(Denomination.THOUSAND,      500));
    withdrawCells2.add(new Cell(Denomination.FIVE_HUNDRED,  500));
    withdrawCells2.add(new Cell(Denomination.TWO_HUNDRED,   500));
    withdrawCells2.add(new Cell(Denomination.HUNDRED,       500));
    withdrawCells2.add(new Cell(Denomination.FIFTY,         500));
    
    List<Cell> depositCells1 = new ArrayList<>();
    depositCells1.add(new Cell(Denomination.FIVE_THOUSAND,  0));
    depositCells1.add(new Cell(Denomination.TWO_THOUSAND,   0));
    depositCells1.add(new Cell(Denomination.THOUSAND,       0));
    depositCells1.add(new Cell(Denomination.FIVE_HUNDRED,   0));
    depositCells1.add(new Cell(Denomination.TWO_HUNDRED,    0));
    depositCells1.add(new Cell(Denomination.HUNDRED,        0));
    depositCells1.add(new Cell(Denomination.FIFTY,          0));
    
    List<Cell> depositCells2 = new ArrayList<>();
    depositCells2.add(new Cell(Denomination.FIVE_THOUSAND, 0));
    depositCells2.add(new Cell(Denomination.TWO_THOUSAND,  0));
    depositCells2.add(new Cell(Denomination.THOUSAND,      0));
    depositCells2.add(new Cell(Denomination.FIVE_HUNDRED,  0));
    depositCells2.add(new Cell(Denomination.TWO_HUNDRED,   0));
    depositCells2.add(new Cell(Denomination.HUNDRED,       0));
    depositCells2.add(new Cell(Denomination.FIFTY,         0));
    
    ATM atm1 = new ATM(withdrawCells1, depositCells1);
    ATM atm2 = new ATM(withdrawCells2, depositCells2);
    
    ATMDepartment atmDepartment = new ATMDepartment(atm1, atm2);
    atmDepartment.setMemento(atm1, atm1.saveToMemento());
    atmDepartment.setMemento(atm2, atm2.saveToMemento());
    
    // Interact with ATMs
    
    System.out.println("ATM 1 / Account ID: 123456");
    atm1.authenticate(new BigInteger("123456"), "0567");
    System.out.println("Balance: " + atm1.getBalance());
    System.out.println("Withdraw cells before operation: " + atm1.getWithdrawCells());
    System.out.println("ATM 1 balance: " + atm1.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    System.out.println("Withdraw sum: 20750");
    atm1.withdraw(20750);
    System.out.println("Withdraw cells after operation: " + atm1.getWithdrawCells());
    System.out.println("ATM 1 balance: " + atm1.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    System.out.println("Balance: " + atm1.getBalance());
    
    System.out.println("Deposit cells before operation: " + atm1.getDepositCells());
    System.out.println("Deposit sum: 1100");
    Map<Denomination, Integer> money = new HashMap<>();
    money.put(Denomination.HUNDRED, 10);
    money.put(Denomination.FIFTY, 2);
    atm1.deposit(money);
    System.out.println("Deposit cells after operation: " + atm1.getDepositCells());
    System.out.println("ATM 1 balance: " + atm1.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    System.out.println("Balance: " + atm1.getBalance());
    atm1.terminate();
    
    System.out.println("\n-----\n");
    
    System.out.println("ATM 1 / Account ID: 654321");
    atm1.authenticate(new BigInteger("654321"), "2469");
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    System.out.println("Withdraw cells before operation: " + atm1.getWithdrawCells());
    System.out.println("Withdraw sum: 650");
    atm1.withdraw(650);
    System.out.println("Withdraw cells after operation: " + atm1.getWithdrawCells());
    System.out.println("ATM 1 balance: " + atm1.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    atm1.terminate();
    
    System.out.println("\n-----\n");
    
    System.out.println("ATM 2 / Account ID: 654321");
    atm2.authenticate(new BigInteger("654321"), "2469");
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    System.out.println("Withdraw cells before operation: " + atm2.getWithdrawCells());
    System.out.println("Withdraw sum: 200");
    atm2.withdraw(200);
    System.out.println("Withdraw cells after operation: " + atm2.getWithdrawCells());
    System.out.println("ATM 2 balance: " + atm2.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    //System.out.println("Balance: " + atm.getBalance()); // access denied
    atm2.terminate();
    
    // Top up ATMs' cells at the end of the day
    
    System.out.println("\n-----\n");
    System.out.println("The end of the day: reset cells from latest memento...");
    atmDepartment.triggerEndOfDayEvent();
    
    // Interact with ATMs once again
    
    System.out.println("\n-----\n");
    
    System.out.println("ATM 1 / Account ID: 123456");
    atm1.authenticate(new BigInteger("123456"), "0567");
    System.out.println("Balance: " + atm1.getBalance());
    System.out.println("Withdraw cells before operation: " + atm1.getWithdrawCells());
    System.out.println("ATM 1 balance: " + atm1.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    System.out.println("Withdraw sum: 100");
    atm1.withdraw(100);
    System.out.println("Withdraw cells after operation: " + atm1.getWithdrawCells());
    System.out.println("ATM 1 balance: " + atm1.getATMBalance());
    System.out.println("ATM Department balance: " + atmDepartment.getBalance());
    System.out.println("Balance: " + atm1.getBalance());
    
  }

}
