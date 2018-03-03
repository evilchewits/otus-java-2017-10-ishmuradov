package com.ishmuradov.otus.homework13.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.ishmuradov.otus.homework13.services.UserService;

@SuppressWarnings("serial")
@WebServlet("/admin")
public class AdminServlet extends AbstractServlet {

  private static final String ADMIN_PAGE_TEMPLATE = "admin.tmpl";
  
  @Autowired
  private UserService userService;

  private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
    Map<String, Object> pageVariables = new HashMap<>();
    pageVariables.put("contextPath", request.getContextPath());
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