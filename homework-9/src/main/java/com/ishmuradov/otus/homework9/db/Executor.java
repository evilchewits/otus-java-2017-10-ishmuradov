package com.ishmuradov.otus.homework9.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
  private final Connection connection;

  public Executor(Connection connection) {
    this.connection = connection;
  }

  public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
    try (Statement stmt = connection.createStatement()) {
      stmt.execute(query);
      try (ResultSet rs = stmt.getResultSet()) {
        return handler.handle(rs);
      }
    }
  }
  
}
