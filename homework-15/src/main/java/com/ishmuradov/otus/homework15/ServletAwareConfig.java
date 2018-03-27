package com.ishmuradov.otus.homework15;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServletAwareConfig extends ServerEndpointConfig.Configurator {
  @Override
  public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
    ServletContext servletContext = ((HttpSession) request.getHttpSession()).getServletContext();
    config.getUserProperties().put("servletContext", servletContext);
  }
}
