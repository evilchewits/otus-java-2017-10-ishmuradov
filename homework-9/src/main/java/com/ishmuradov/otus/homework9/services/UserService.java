package com.ishmuradov.otus.homework9.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ishmuradov.otus.homework9.db.UserRepository;
import com.ishmuradov.otus.homework9.db.UserRepositoryImpl;
import com.ishmuradov.otus.homework9.model.User;

public class UserService {
  private UserRepository userRepository;
  
  public UserService(Connection connection) {
    userRepository = new UserRepositoryImpl(connection);
  }
  
  public void addUsers(List<User> users) throws SQLException {
    for (User user : users) {
      userRepository.save(user);
    }
  }
  
  public void printUser(long id) throws SQLException {
    System.out.println(userRepository.load(id).toString());
  }

}
