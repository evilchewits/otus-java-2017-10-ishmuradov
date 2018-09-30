package com.ishmuradov.otus.homework15;

import com.ishmuradov.otus.homework15.messagesystem.Address;
import com.ishmuradov.otus.homework15.messagesystem.Addressee;
import com.ishmuradov.otus.homework15.messagesystem.Message;

/**
 * Created by tully.
 */
public abstract class MsgToFrontend extends Message {

  public MsgToFrontend(Address from, Address to) {
    super(from, to);
  }

  @Override
  public void exec(Addressee addressee) {
    if (addressee instanceof FrontendService) {
      exec((FrontendService) addressee);
    } else {
      // todo error!
    }
  }

  public abstract void exec(FrontendService frontendService);
}