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

import com.ishmuradov.otus.homework15.FrontendService;
import com.ishmuradov.otus.homework15.MessageSystemContext;
import com.ishmuradov.otus.homework15.MsgGetCacheStatistics;
import com.ishmuradov.otus.homework15.ServletAwareConfig;
import com.ishmuradov.otus.homework15.messagesystem.Address;
import com.ishmuradov.otus.homework15.messagesystem.Message;
import com.ishmuradov.otus.homework15.messagesystem.MessageSystem;

@ServerEndpoint(value = "/cache", configurator = ServletAwareConfig.class)
public class CacheReviewerEndpoint implements FrontendService {
  private final static Logger logger = Logger.getLogger(CacheReviewerEndpoint.class);

  /*
   * Problems with mixing @ServerEndpointand @Autowired, and how to solve them:
   * https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-
   * httpsession-in-onmessage-of-a-jsr-356-serverendpo
   * https://stackoverflow.com/questions/17936440/accessing-httpsession-from-
   * httpservletrequest-in-a-web-socket-serverendpoint
   */
  // private UserService userService;
  private MessageSystemContext context;
  private final Address address = new Address();
  private Session wsSession;

  @OnOpen
  public void onOpen(Session session, EndpointConfig config) {
    logger.info("Websocket: onOpen");
    this.wsSession = session;
    ServletContext servletContext = (ServletContext) config.getUserProperties().get("servletContext");
    // userService = ((AbstractApplicationContext)
    // servletContext.getAttribute("applicationContext"))
    // .getBean("userService", UserService.class);
    context = ((AbstractApplicationContext) servletContext.getAttribute("applicationContext"))
        .getBean("messageSystemContext", MessageSystemContext.class);
  }

  @OnMessage
  public void onMessage(String message) {
    logger.info("Websocket: onMessage");
    handleRequest(message);
  }

  @OnClose
  public void onClose() {
    logger.info("Websocket: onClose");
  }

  @OnError
  public void onError(Throwable throwable) {
    logger.info("Websocket: onError");
  }

  @Override
  public Address getAddress() {
    return address;
  }

  @Override
  public MessageSystem getMS() {
    return context.getMessageSystem();
  }

  @PostConstruct
  @Override
  public void init() {
    context.setFrontAddress(address);
    context.getMessageSystem().addAddressee(this);
  }

  @Override
  public void handleRequest(String request) {
    if (!request.equals("getStatistics")) {
      throw new IllegalArgumentException("Unknown API call: " + request);
    }
    Message message = new MsgGetCacheStatistics(getAddress(), context.getDbAddress());
    context.getMessageSystem().sendMessage(message);

  }

  @Override
  public void handleResponse(String response) {
    wsSession.getAsyncRemote().sendText(response);
  }
}
