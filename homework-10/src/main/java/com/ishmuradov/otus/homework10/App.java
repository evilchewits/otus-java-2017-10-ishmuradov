package com.ishmuradov.otus.homework10;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ishmuradov.otus.homework10.model.Address;
import com.ishmuradov.otus.homework10.model.Phone;
import com.ishmuradov.otus.homework10.model.User;
import com.ishmuradov.otus.homework10.services.UserService;

public class App {

  public static void main(String[] args) {
    try {
      UserService userService = new UserService();
      
      //System.out.println(userService.countUsers());
      
      List<Phone> phones = new ArrayList<>();
      phones.add(new Phone("422-12-60"));
      phones.add(new Phone("+7 910 22 86 60"));
      
      userService.addUsers(Arrays.asList(
          new User("Boris", 32, new Address("ul. Pushkina", 23), phones),
          new User("Eugeny", 21, new Address("per. Svetlogorsky", 90), null)
      ));
      
      //System.out.println(userService.countUsers());
      
      userService.printUser(1L);
      userService.printUser(2L);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
