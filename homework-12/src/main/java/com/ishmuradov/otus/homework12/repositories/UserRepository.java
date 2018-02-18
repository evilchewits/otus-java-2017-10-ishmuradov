package com.ishmuradov.otus.homework12.repositories;

import com.ishmuradov.otus.homework12.model.User;

public interface UserRepository extends Repository<User> {

  long getSaveCount();

  long getLoadCount();

}
