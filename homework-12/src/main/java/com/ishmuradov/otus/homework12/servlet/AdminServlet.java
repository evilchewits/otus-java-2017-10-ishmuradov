package com.ishmuradov.otus.homework12.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ishmuradov.otus.homework12.services.UserService;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {

  private static final String ADMIN_PAGE_TEMPLATE = "admin.tmpl";

  private UserService userService;

  public AdminServlet(UserService userService) {
    this.userService = userService;
  }

  private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
    Map<String, Object> pageVariables = new HashMap<>();
    pageVariables.put("cacheParams", userService.getCacheParams());
    pageVariables.put("statistics", userService.getStatistics());

    return pageVariables;
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Map<String, Object> pageVariables = createPageVariablesMap(request);
    response.getWriter().println(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));
    ServletUtils.setOK(response);
  }
}