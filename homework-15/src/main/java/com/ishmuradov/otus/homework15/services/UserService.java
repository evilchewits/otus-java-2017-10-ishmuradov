package com.ishmuradov.otus.homework15.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ishmuradov.otus.homework15.DBService;
import com.ishmuradov.otus.homework15.MessageSystemContext;
import com.ishmuradov.otus.homework15.cache.Cache;
import com.ishmuradov.otus.homework15.cache.CacheElement;
import com.ishmuradov.otus.homework15.cache.CacheImpl;
import com.ishmuradov.otus.homework15.messagesystem.Address;
import com.ishmuradov.otus.homework15.messagesystem.MessageSystem;
import com.ishmuradov.otus.homework15.model.User;
import com.ishmuradov.otus.homework15.repositories.RepositoryFactory;
import com.ishmuradov.otus.homework15.repositories.UserRepository;

@Service
public class UserService implements DBService {
  final static Logger logger = Logger.getLogger(UserService.class);
  private static Gson gson = new Gson();

  public static final int CACHE_SIZE = 1000;
  private final Cache<Long, User> cache;

  private UserRepository userRepository;

  @Autowired
  private MessageSystemContext context;
  private final Address address = new Address();

  public UserService() {
    super();
    logger.info("Instantiating UserService");
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

  public User getUser(long id) throws SQLException {
    CacheElement<Long, User> cachedElement = cache.get(id);
    User cachedUser = null;
    if (cachedElement != null) {
      cachedUser = cachedElement.getValue();
    }
    User user = (cachedUser != null) ? cachedUser : userRepository.load(id);
    if (cachedElement == null) {
      cache.put(new CacheElement<Long, User>(user.getId(), user));
    }
    return user;
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

  @Override
  public Address getAddress() {
    return address;
  }

  @Override
  public MessageSystem getMS() {
    return context.getMessageSystem();
  }

  @PostConstruct
  @Override
  public void init() {
    context.setDbAddress(address);
    context.getMessageSystem().addAddressee(this);
  }

  @Override
  public String getCacheStatistics() {
    return gson.toJson(getStatistics());
  }
}
