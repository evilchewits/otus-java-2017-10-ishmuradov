package com.ishmuradov.otus.homework12.repositories;

import java.sql.SQLException;

import com.ishmuradov.otus.homework12.model.Entity;

public interface Repository<T extends Entity> {

  T save(T entity) throws SQLException;

  T load(long id) throws SQLException;

  long count() throws SQLException;

}
