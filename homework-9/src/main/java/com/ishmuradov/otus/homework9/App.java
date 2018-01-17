package com.ishmuradov.otus.homework9;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {

  public static final String DB_CONFIG = "config.properties";

  public static void main(String[] args) {

    // Read DB connection params from config.properties
    
    Properties prop = new Properties();

    try (InputStream input = App.class.getClassLoader().getResourceAsStream(DB_CONFIG)) {

      if (input == null) {
        System.out.println("Unable to find " + DB_CONFIG);
        return;
      }

      prop.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    
    // Connect to DB
    
    System.out.println("Connecting to DB...\nURL: " + prop.getProperty("datasource.url") + "\nuser: "
        + prop.getProperty("datasource.username"));

    try (
        Connection conn = DriverManager.getConnection(
            prop.getProperty("datasource.url"),
            prop.getProperty("datasource.username"),
            prop.getProperty("datasource.password"));
        Statement stmt = conn.createStatement()) {
      
      System.out.println("Connected.");
      System.out.println("Creating DB tables...");
      
      Class.forName(prop.getProperty("datasource.driverClassName"));

      // Create USER table in DB
      
      String sql =
          "CREATE TABLE user " + 
          "(id BIGINT(20) NOT NULL, " + 
          " name VARCHAR(255), " + 
          " age INTEGER(3), " + 
          " PRIMARY KEY (id))";
      stmt.executeUpdate(sql);
      
      System.out.println("Created.");
      
      // TODO: read-write operations with DB
      
      
      
    } catch (SQLException se) {
      se.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
