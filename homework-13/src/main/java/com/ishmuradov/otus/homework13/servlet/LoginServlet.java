package com.ishmuradov.otus.homework13.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

  private final String username = "admin";
  private final String password = "admin";

  public static final String REQUEST_PARAM_USERNAME = "username";
  public static final String REQUEST_PARAM_PASSWORD = "password";
  private static final String VAR_NAME_IS_AUTHENTICATED = "isAuthenticated";
  private static final String LOGIN_PAGE_TEMPLATE = "login.tmpl";

  private String getPage(Map<String, Object> pageVariables) throws IOException {
    return TemplateProcessor.instance().getPage(LOGIN_PAGE_TEMPLATE, pageVariables);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String requestUsername = request.getParameter(REQUEST_PARAM_USERNAME);
    String requestPassword = request.getParameter(REQUEST_PARAM_PASSWORD);
    HttpSession currSession = request.getSession(false);
    boolean isAuthenticated;
    if (currSession != null) {
      isAuthenticated = (boolean) currSession.getAttribute(VAR_NAME_IS_AUTHENTICATED);
    } else {
      isAuthenticated = false;
    }
    Map<String, Object> pageVariables = new HashMap<>();
    pageVariables.put("contextPath", request.getContextPath());

    if (this.username.equals(requestUsername) && this.password.equals(requestPassword)) {
      isAuthenticated = true;

      // invalidate the old session if exists
      if (currSession != null) {
        currSession.invalidate();
      }

      // generate a new session
      HttpSession newSession = request.getSession(true);
      newSession.setAttribute(VAR_NAME_IS_AUTHENTICATED, isAuthenticated);
      newSession.setMaxInactiveInterval(30 * 60);
    } else if (requestUsername != null && requestPassword != null) {
      response.getWriter().println("<div class=\"error\">Either username or password is wrong</div>");
    }

    pageVariables.put(VAR_NAME_IS_AUTHENTICATED, isAuthenticated);
    response.getWriter().println(getPage(pageVariables));
    ServletUtils.setOK(response);
  }
}