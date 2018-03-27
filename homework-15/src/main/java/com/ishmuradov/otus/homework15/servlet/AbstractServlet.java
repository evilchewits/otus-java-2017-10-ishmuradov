package com.ishmuradov.otus.homework15.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * All subclasses of this abstract servlet class will be @Autowire aware.
 * 
 * @author Ishmuradov
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractServlet extends HttpServlet {

  protected AutowireCapableBeanFactory ctx;

  @Override
  public void init() throws ServletException {
    super.init();
    ctx = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getAutowireCapableBeanFactory();
    ctx.autowireBean(this);
  }

}
