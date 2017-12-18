package com.ishmuradov.otus.homework6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ishmuradov.otus.homework6.ATM.CellStateMemento;

/**
 * Patterns: Observer, Memento
 * 
 * @author Ishmuradov
 *
 */
public class ATMDepartment {
  private Set<ATM> atms;
  private Map<ATM, CellStateMemento> mementos;

  public ATMDepartment(ATM... atms) {
    this.atms = new HashSet<>();
    this.mementos = new HashMap<>();
    
    for (ATM atm : atms) {
      this.atms.add(atm);
      this.mementos.put(atm, atm.saveToMemento());
    }
  }
  
  public void triggerEndOfDayEvent() {
    atms.forEach(atm -> {
      EndOfDayEvent event = new EndOfDayEvent();
      event.setMemento(getMemento(atm));
      atm.notify(event);
    });
  }
  
  public long getBalance() {
    return atms.stream().mapToLong(atm -> atm.getATMBalance()).sum();
  }

  public CellStateMemento getMemento(ATM atm) {
    return mementos.get(atm);
  }

  public void setMemento(ATM atm, CellStateMemento memento) {
    mementos.put(atm, memento);
  }
  
}
