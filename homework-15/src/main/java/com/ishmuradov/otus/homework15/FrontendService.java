package com.ishmuradov.otus.homework15;

import com.ishmuradov.otus.homework15.messagesystem.Addressee;

/**
 * Created by tully.
 */
public interface FrontendService extends Addressee {
    void init();

    void handleRequest(String message);
    
    void handleResponse(String message);

    //void addUser(int id, String name);
}
