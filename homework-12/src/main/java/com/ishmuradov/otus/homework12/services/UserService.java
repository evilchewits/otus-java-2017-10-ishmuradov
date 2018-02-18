package com.ishmuradov.otus.homework12.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ishmuradov.otus.homework12.cache.Cache;
import com.ishmuradov.otus.homework12.cache.CacheElement;
import com.ishmuradov.otus.homework12.cache.CacheImpl;
import com.ishmuradov.otus.homework12.model.User;
import com.ishmuradov.otus.homework12.repositories.RepositoryFactory;
import com.ishmuradov.otus.homework12.repositories.UserRepository;

public class UserService {

  public static final int CACHE_SIZE = 1000;
  private final Cache<Long, User> cache;

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
    CacheElement<Long, User> cachedElement = cache.get(id);
    User cachedUser = null;
    if (cachedElement != null) {
      cachedUser = cachedElement.getValue();
    }
    User user = (cachedUser != null) ? cachedUser : userRepository.load(id);
    if (cachedElement == null) {
      cache.put(new CacheElement<Long, User>(user.getId(), user));
    }
    System.out.println(user.toString());
  }

  public long countUsers() throws SQLException {
    return userRepository.count();
  }
  

  public Map<String, String> getCacheParams() {
    return cache.getCacheParams();
  }
  
  public Map<String, String> getStatistics() {
    Map<String, String> statistics = new HashMap<>();
    statistics.put("Cache hits", String.valueOf(cache.getHitCount()));
    statistics.put("Cache misses", String.valueOf(cache.getMissCount()));
    statistics.put("DB save count", String.valueOf(userRepository.getSaveCount()));
    statistics.put("DB load count", String.valueOf(userRepository.getLoadCount()));

    return Collections.unmodifiableMap(statistics);
  }
  
  public void printStatistics() {
    Map<String, String> statistics = getStatistics();
    statistics.forEach((k, v) -> System.out.println(k + " : " + v));
  }
}
