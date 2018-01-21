package com.ishmuradov.otus.homework9.db;

import java.sql.SQLException;

import com.ishmuradov.otus.homework9.model.Entity;

public interface Repository<T extends Entity> {
  void save(T entity) throws SQLException;

  T load(long id) throws SQLException;
}
