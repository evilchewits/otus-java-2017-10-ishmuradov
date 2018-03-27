package com.ishmuradov.otus.homework15.websocket;

import javax.servlet.ServletContext;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;

import com.google.gson.Gson;
import com.ishmuradov.otus.homework15.ServletAwareConfig;
import com.ishmuradov.otus.homework15.services.UserService;

@ServerEndpoint(value = "/cache", configurator = ServletAwareConfig.class)
public class CacheReviewerEndpoint {
  final static Logger logger = Logger.getLogger(CacheReviewerEndpoint.class);
  private static Gson gson = new Gson();

  /*
   * Problems with mixing @ServerEndpointand @Autowired, and how to solve them:
   * https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-
   * httpsession-in-onmessage-of-a-jsr-356-serverendpo
   * https://stackoverflow.com/questions/17936440/accessing-httpsession-from-
   * httpservletrequest-in-a-web-socket-serverendpoint
   */
  private UserService userService;

  private Session wsSession;

  @OnOpen
  public void onOpen(Session session, EndpointConfig config) {
    logger.debug("Websocket: onOpen");
    this.wsSession = session;
    ServletContext servletContext = (ServletContext) config.getUserProperties().get("servletContext");
    userService = ((AbstractApplicationContext) servletContext.getAttribute("applicationContext"))
        .getBean("userService", UserService.class);
  }

  @OnMessage
  public void onMessage(String message) {
    logger.debug("Websocket: onMessage");
    if (!message.equals("getStatistics")) {
      throw new IllegalArgumentException("Unknown API call: " + message);
    }
    wsSession.getAsyncRemote().sendText(gson.toJson(userService.getStatistics()));
  }

  @OnClose
  public void onClose() {
    logger.debug("Websocket: onClose");
  }

  @OnError
  public void onError(Throwable throwable) {
    logger.debug("Websocket: onError");
  }
}
