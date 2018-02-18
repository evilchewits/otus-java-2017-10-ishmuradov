package com.ishmuradov.otus.homework12.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

  private ServletContext context;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.context = filterConfig.getServletContext();
    this.context.log("AuthenticationFilter initialized");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession(false);

    if (!req.getRequestURI().equals("/login") && session == null) {
      this.context.log("Unauthorized access request");
      res.sendRedirect(req.getContextPath() + "/login");
    } else {
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

}
