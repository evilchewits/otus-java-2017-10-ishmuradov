package com.ishmuradov.otus.homework11;

import java.sql.SQLException;

import com.ishmuradov.otus.homework11.model.Address;
import com.ishmuradov.otus.homework11.model.User;
import com.ishmuradov.otus.homework11.services.UserService;

/**
 * Tests with JVM 9 x64:
 * 
 * 1. -Xmx8m -Xms8m, CACHE_SIZE = 100, CacheElement with WeakReferences
 *    -Xmx9m -Xms9m, CACHE_SIZE = 100, CacheElement with WeakReferences
 *    
 * All weak references are cleared by GC:
 * 
 * Cache hits: 0
 * Cache misses: 200
 * DB save count: 200
 * DB load count: 200
 * 
 * 2. -Xmx80m -Xms80m, CACHE_SIZE = 100, CacheElement with WeakReferences
 * 
 * Memory is enough, GC does not clean up weak references:
 * 
 * Cache hits: 100
 * Cache misses: 100
 * DB save count: 200
 * DB load count: 100
 * 
 * 3. -Xmx8m -Xms8m, CACHE_SIZE = 100, CacheElement with SoftReferences
 * 
 * All soft references were cleared by GC:
 * 
 * Cache hits: 0
 * Cache misses: 200
 * DB save count: 200
 * DB load count: 200
 * 
 * 4. -Xmx9m -Xms9m, CACHE_SIZE = 100, CacheElement with SoftReferences
 * 
 * Soft references were not removed, while the memory size was increased by only 1Mb:
 * 
 * Cache hits: 100
 * Cache misses: 100
 * DB save count: 200
 * DB load count: 100
 * 
 * @author Ishmuradov
 *
 */
public class App {

  public static void main(String[] args) {
    int size = UserService.CACHE_SIZE;
    
    try {
      UserService userService = new UserService();
      
      //System.out.println(userService.countUsers());

      for (int i = 1; i <= size * 2; i++) {
        userService.addUser(new User("User " + i, 20, new Address("pr. Lenina", i), null));
      }
      
      //System.out.println(userService.countUsers());
      
      for (int i = 1; i <= size * 2; i++) {
        userService.printUser(i);
      }
      
      userService.printStatistics();
      
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
