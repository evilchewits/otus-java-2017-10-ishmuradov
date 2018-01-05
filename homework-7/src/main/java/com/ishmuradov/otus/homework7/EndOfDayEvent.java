package com.ishmuradov.otus.homework7;

public class EndOfDayEvent implements Event {

  private ATM atm;
  
  public EndOfDayEvent(ATM atm) {
    super();
    this.atm = atm;
  }

  @Override
  public String getName() {
    return "END_OF_DAY_EVENT";
  }

  @Override
  public void execute() {
    atm.restoreFromMemento();
  }
  
}
