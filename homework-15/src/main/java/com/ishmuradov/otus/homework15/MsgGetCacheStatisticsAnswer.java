package com.ishmuradov.otus.homework15;

import org.springframework.beans.factory.annotation.Autowired;

import com.ishmuradov.otus.homework15.messagesystem.Address;
import com.ishmuradov.otus.homework15.services.UserService;

/**
 * Created by tully.
 */
public class MsgGetCacheStatisticsAnswer extends MsgToFrontend {

  @Autowired
  private UserService userService;

  public MsgGetCacheStatisticsAnswer(Address from, Address to) {
    super(from, to);
  }

  @Override
  public void exec(FrontendService frontendService) {
    frontendService.handleResponse(userService.getCacheStatistics());
  }
}
