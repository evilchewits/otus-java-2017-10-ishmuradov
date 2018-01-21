package com.ishmuradov.otus.homework9.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.ishmuradov.otus.homework9.App;

public class DbHelper {
  
  public static final String DB_CONFIG = "config.properties";
  
  private static Properties getDbConfiguration() {
    Properties prop = new Properties();

    try (InputStream input = App.class.getClassLoader().getResourceAsStream(DB_CONFIG)) {
      prop.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Could not load DB configuration from file: " + DB_CONFIG);
    }
    
    return prop;
  }
  
  public static Connection getConnection() throws SQLException {
    Properties prop = getDbConfiguration();

    return DriverManager.getConnection(
        prop.getProperty("datasource.url"),
        prop.getProperty("datasource.username"),
        prop.getProperty("datasource.password"));
  }
  
  public static void prepareTables(Connection connection) {
    try (Statement stmt = connection.createStatement()) {
      // Create USER table in DB
      String sql =
          "CREATE TABLE user " + 
          "(id BIGINT(20) NOT NULL AUTO_INCREMENT, " + 
          " name VARCHAR(255), " + 
          " age INTEGER(3), " + 
          /* " married BOOLEAN NOT NULL DEFAULT FALSE, " + */
          " PRIMARY KEY (id))";
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
