package com.ishmuradov.otus.homework15.repositories;

import com.ishmuradov.otus.homework15.model.User;

public interface UserRepository extends Repository<User> {

  long getSaveCount();

  long getLoadCount();

}
