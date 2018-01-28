package com.ishmuradov.otus.homework10.services;

import java.sql.SQLException;
import java.util.List;

import com.ishmuradov.otus.homework10.model.User;
import com.ishmuradov.otus.homework10.repositories.RepositoryFactory;
import com.ishmuradov.otus.homework10.repositories.UserRepository;

public class UserService {

  private UserRepository userRepository;

  public UserService() {
    RepositoryFactory rf = RepositoryFactory.getRepositoryFactory();
    userRepository = rf.getUserRepository();
  }

  public void addUsers(List<User> users) throws SQLException {
    for (User user : users) {
      userRepository.save(user);
    }
  }

  public void printUser(long id) throws SQLException {
    User user = userRepository.load(id);
    System.out.println(user.toString());
  }

  public long countUsers() throws SQLException {
    return userRepository.count();
  }

}
