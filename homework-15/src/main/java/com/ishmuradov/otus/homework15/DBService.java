package com.ishmuradov.otus.homework15;

import com.ishmuradov.otus.homework15.messagesystem.Addressee;

/**
 * Created by tully.
 */
public interface DBService extends Addressee {
    void init();

    //int getUserId(String name);
    
    String getCacheStatistics();
}
