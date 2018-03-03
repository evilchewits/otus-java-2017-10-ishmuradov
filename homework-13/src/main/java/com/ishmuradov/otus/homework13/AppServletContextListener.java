package com.ishmuradov.otus.homework13;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class AppServletContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    sce.getServletContext().setAttribute("applicationContext", ctx);
    // Initialize users and run infinite loop for getting them
    ctx.getBean("appWorker", AppWorker.class).start();
  }

}
