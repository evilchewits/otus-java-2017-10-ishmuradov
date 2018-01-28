package com.ishmuradov.otus.homework10.repositories;

import java.sql.SQLException;

import com.ishmuradov.otus.homework10.model.Entity;

public interface Repository<T extends Entity> {

  void save(T entity) throws SQLException;

  T load(long id) throws SQLException;

  long count() throws SQLException;

}
