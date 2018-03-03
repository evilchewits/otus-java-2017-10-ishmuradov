package com.ishmuradov.otus.homework13;

import java.sql.SQLException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ishmuradov.otus.homework13.model.Address;
import com.ishmuradov.otus.homework13.model.User;
import com.ishmuradov.otus.homework13.services.UserService;

@Component
public class AppWorker extends Thread {
  final static Logger log = Logger.getLogger(AppWorker.class);

  @Autowired
  private UserService userService;
  
  int size = UserService.CACHE_SIZE;
  int doubleSize = size * 2;
  int delay = 50;
  double stdDeviation = 0.3;
  Random random = new Random();

  public AppWorker() {
    super("AppWorker");
    log.info("Instantiating AppWorker");
  }

  @Override
  public void run() {
    try {
      for (int i = 1; i <= doubleSize; i++) {
        userService.addUser(new User("User " + i, 20, new Address("pr. Lenina", i), null));
      }
      for (;;) {
        int randomNumber = (int) ((random.nextGaussian() * stdDeviation + 1) * doubleSize);
        int randomIndex = (randomNumber < 1 || randomNumber > doubleSize) ? (int) (Math.random() * doubleSize + 1)
            : randomNumber;
        userService.printUser(randomIndex);
        Thread.sleep(delay);
      }
    } catch (SQLException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
