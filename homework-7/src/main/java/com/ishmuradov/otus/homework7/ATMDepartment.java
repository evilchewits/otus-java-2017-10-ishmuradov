package com.ishmuradov.otus.homework7;

import java.util.HashSet;
import java.util.Set;

/**
 * Patterns: Observer
 * 
 * @author Ishmuradov
 *
 */
public class ATMDepartment {
  private Set<ATM> atms;

  public ATMDepartment(ATM... atms) {
    this.atms = new HashSet<>();

    for (ATM atm : atms) {
      this.atms.add(atm);
    }
  }
  
  public void triggerEndOfDayEvent() {
    atms.forEach(atm -> atm.notify(new EndOfDayEvent(atm)));
  }
  
  public long getBalance() {
    return atms.stream().mapToLong(atm -> atm.getATMBalance()).sum();
  }

}
