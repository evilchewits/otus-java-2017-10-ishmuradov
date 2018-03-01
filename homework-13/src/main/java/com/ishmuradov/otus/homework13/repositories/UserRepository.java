package com.ishmuradov.otus.homework13.repositories;

import com.ishmuradov.otus.homework13.model.User;

public interface UserRepository extends Repository<User> {

  long getSaveCount();

  long getLoadCount();

}
