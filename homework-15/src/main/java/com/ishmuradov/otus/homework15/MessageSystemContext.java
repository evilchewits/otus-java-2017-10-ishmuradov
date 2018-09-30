package com.ishmuradov.otus.homework15;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ishmuradov.otus.homework15.messagesystem.Address;
import com.ishmuradov.otus.homework15.messagesystem.MessageSystem;

/**
 * Created by tully.
 */
@Component
public class MessageSystemContext {
  final static Logger logger = Logger.getLogger(MessageSystemContext.class);
  
  public MessageSystemContext() {
    super();
    logger.info("Instantiating MessageSystemContext");
  }

  @Autowired
  private MessageSystem messageSystem;

  private Address frontAddress;
  private Address dbAddress;

  public MessageSystem getMessageSystem() {
    return messageSystem;
  }

  public Address getFrontAddress() {
    return frontAddress;
  }

  public void setFrontAddress(Address frontAddress) {
    this.frontAddress = frontAddress;
  }

  public Address getDbAddress() {
    return dbAddress;
  }

  public void setDbAddress(Address dbAddress) {
    this.dbAddress = dbAddress;
  }
}
