//package com.ishmuradov.otus.homework13;
//
//import java.sql.SQLException;
////import java.util.EnumSet;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
////import javax.servlet.DispatcherType;
//
////import org.eclipse.jetty.server.Server;
////import org.eclipse.jetty.server.handler.HandlerList;
////import org.eclipse.jetty.server.handler.ResourceHandler;
////import org.eclipse.jetty.servlet.ServletContextHandler;
////import org.eclipse.jetty.servlet.ServletHolder;
//
////import com.ishmuradov.otus.homework13.model.Address;
////import com.ishmuradov.otus.homework13.model.User;
////import com.ishmuradov.otus.homework13.services.UserService;
////import com.ishmuradov.otus.homework13.servlet.AdminServlet;
////import com.ishmuradov.otus.homework13.servlet.AuthenticationFilter;
////import com.ishmuradov.otus.homework13.servlet.LoginServlet;
//
///**
// * 
// * @author Ishmuradov
// *
// */
//public class App {
////  public final static int PORT = 8090;
////  public final static String PUBLIC_HTML = "public_html";
//  
//  public static void main(String[] args) {
//    
//    // Cache
//    
////    int size = UserService.CACHE_SIZE;
////    int doubleSize = size * 2;
////    UserService userService;
////    
////    try {
////      userService = new UserService();
////      
////      //System.out.println(userService.countUsers());
////
////      for (int i = 1; i <= doubleSize; i++) {
////        userService.addUser(new User("User " + i, 20, new Address("pr. Lenina", i), null));
////      }
////      
////      //System.out.println(userService.countUsers());
////      
////      for (int i = 1; i <= doubleSize; i++) {
////        userService.printUser((int) (Math.random() * doubleSize + 1));
////      }
////      
////      userService.printStatistics();
////      
////    } catch (SQLException e) {
////      throw new RuntimeException(e);
////    }
//
//    // Server
//    
////    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
////    context.addFilter(AuthenticationFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
////    context.addServlet(LoginServlet.class, "/login");
////    context.addServlet(LogoutServlet.class, "/logout");
////    context.addServlet(new ServletHolder(new AdminServlet(userService)), "/admin");
////
////    ResourceHandler resourceHandler = new ResourceHandler();
////    Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
////    resourceHandler.setResourceBase(PUBLIC_HTML);
////    
////    Server server = new Server(PORT);
////    server.setHandler(new HandlerList(resourceHandler, context));
////
////    server.start();
////    server.join();
//  }
//
//}
