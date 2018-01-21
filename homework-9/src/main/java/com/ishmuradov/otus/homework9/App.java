package com.ishmuradov.otus.homework9;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import com.ishmuradov.otus.homework9.db.DbHelper;
import com.ishmuradov.otus.homework9.model.User;
import com.ishmuradov.otus.homework9.services.UserService;

public class App {

  public static void main(String[] args) {
    try (Connection connection = DbHelper.getConnection()) {
      DbHelper.prepareTables(connection);
      UserService userService = new UserService(connection);
      userService.addUsers(Arrays.asList(
          // Class User is ready for adding new fields.
          // Fields will automatically be mapped from/to a DB.
          // Example: uncomment "married" field in User class
          // and DbHelper#prepareTables(Connection) method.
          new User("Boris", 32 /*, true */),
          new User("Eugeny", 21 /*, false */)
      ));
      userService.printUser(1L);
      userService.printUser(2L);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
