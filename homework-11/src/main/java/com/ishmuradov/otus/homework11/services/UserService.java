package com.ishmuradov.otus.homework11.services;

import java.sql.SQLException;
import java.util.List;

import com.ishmuradov.otus.homework11.cache.Cache;
import com.ishmuradov.otus.homework11.cache.CacheElement;
import com.ishmuradov.otus.homework11.cache.CacheImpl;
import com.ishmuradov.otus.homework11.model.User;
import com.ishmuradov.otus.homework11.repositories.RepositoryFactory;
import com.ishmuradov.otus.homework11.repositories.UserRepository;

public class UserService {

  public static final int CACHE_SIZE = 100;
  private Cache<Long, User> cache;

  private UserRepository userRepository;

  public UserService() {
    RepositoryFactory rf = RepositoryFactory.getRepositoryFactory();
    userRepository = rf.getUserRepository();
    this.cache = new CacheImpl<Long, User>(CACHE_SIZE, 0, 0, true);
  }

  public void addUsers(List<User> users) throws SQLException {
    for (User user : users) {
      User persistedUser = userRepository.save(user);
      cache.put(new CacheElement<Long, User>(persistedUser.getId(), persistedUser));
    }
  }

  public void addUser(User user) throws SQLException {
    User persistedUser = userRepository.save(user);
    cache.put(new CacheElement<Long, User>(persistedUser.getId(), persistedUser));
  }

  public void printUser(long id) throws SQLException {
    CacheElement<Long, User> cachedUser = cache.get(id);
    User user = (cachedUser != null && cachedUser.getValue() != null) ? cachedUser.getValue() : userRepository.load(id);
    System.out.println(user.toString());
  }

  public long countUsers() throws SQLException {
    return userRepository.count();
  }
  
  public void printStatistics() {
    System.out.println("Cache hits: " + cache.getHitCount());
    System.out.println("Cache misses: " + cache.getMissCount());
    System.out.println("DB save count: " + userRepository.getSaveCount());
    System.out.println("DB load count: " + userRepository.getLoadCount());
  }

}
