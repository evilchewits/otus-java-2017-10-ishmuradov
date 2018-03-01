package com.ishmuradov.otus.homework13;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ComponentScan
public class AppServletContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContextListener.super.contextInitialized(sce);

    //System.out.println(userService);
    /*
    int size = UserService.CACHE_SIZE;
    int doubleSize = size * 2;
    
    try {
//      userService = new UserService();
//      
      //System.out.println(userService.countUsers());

      for (int i = 1; i <= doubleSize; i++) {
        userService.addUser(new User("User " + i, 20, new Address("pr. Lenina", i), null));
      }
      
      //System.out.println(userService.countUsers());
      
      for (int i = 1; i <= doubleSize; i++) {
        userService.printUser((int) (Math.random() * doubleSize + 1));
      }
      
      userService.printStatistics();
      
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    */
  }

}
