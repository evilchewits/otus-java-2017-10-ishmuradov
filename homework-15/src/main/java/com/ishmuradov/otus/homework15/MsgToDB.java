package com.ishmuradov.otus.homework15;

import com.ishmuradov.otus.homework15.DBService;
import com.ishmuradov.otus.homework15.messagesystem.Address;
import com.ishmuradov.otus.homework15.messagesystem.Addressee;
import com.ishmuradov.otus.homework15.messagesystem.Message;

/**
 * Created by tully.
 */
public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
