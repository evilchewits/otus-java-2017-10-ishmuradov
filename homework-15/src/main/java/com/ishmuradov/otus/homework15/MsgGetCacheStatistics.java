package com.ishmuradov.otus.homework15;

import com.ishmuradov.otus.homework15.messagesystem.Address;

/**
 * Created by tully.
 */
public class MsgGetCacheStatistics extends MsgToDB {
  public MsgGetCacheStatistics(Address from, Address to) {
    super(from, to);
  }

  @Override
  public void exec(DBService dbService) {
    dbService.getMS().sendMessage(new MsgGetCacheStatisticsAnswer(getTo(), getFrom()));
  }
}
