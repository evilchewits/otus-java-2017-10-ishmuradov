package com.ishmuradov.otus.homework6;

import com.ishmuradov.otus.homework6.ATM.CellStateMemento;

public class EndOfDayEvent implements Event {

  private CellStateMemento memento;
  
  @Override
  public String getName() {
    return "END_OF_DAY_EVENT";
  }

  public CellStateMemento getMemento() {
    return memento;
  }

  public void setMemento(CellStateMemento memento) {
    this.memento = memento;
  }
  
}
