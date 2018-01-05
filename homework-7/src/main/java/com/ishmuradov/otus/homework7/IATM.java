package com.ishmuradov.otus.homework7;

import java.math.BigInteger;
import java.util.Map;

public interface IATM {
  void authenticate(BigInteger id, String pin);

  void withdraw(long ammount);

  void deposit(Map<Denomination, Integer> money);

  long getBalance();

  void terminate();
}
