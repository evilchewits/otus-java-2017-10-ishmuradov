package com.ishmuradov.otus.homework15.servlet;

import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
  private ServletUtils() {
    super();
  }

  public static void setOK(HttpServletResponse response) {
    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);
  }
}
