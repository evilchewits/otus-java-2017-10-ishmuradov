package com.ishmuradov.otus.homework9.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ishmuradov.otus.homework9.model.User;
import com.ishmuradov.otus.homework9.utils.ReflectionHelper;

public class UserRepositoryImpl implements UserRepository {
  private static final String SELECT_USER = "select * from user where id = %s";
  private static final String INSERT_USER = "insert into user (%s) values (%s)";

  private Executor exec;

  public UserRepositoryImpl(Connection connection) {
    exec = new Executor(connection);
  }

  @Override
  public void save(User user) throws SQLException {
    List<Field> fields = Arrays.asList(User.class.getDeclaredFields());
    String columns = fields.stream().map(f -> f.getName()).collect(Collectors.joining(", "));
    String values = fields.stream()
        .map(f -> String.join("", "'", ReflectionHelper.getFieldValue(user, f.getName()).toString(), "'"))
        .collect(Collectors.joining(", "));
    exec.execQuery(String.format(INSERT_USER, columns, values), rs -> rs);
  }

  @Override
  public User load(long id) throws SQLException {
    return exec.execQuery(String.format(SELECT_USER, id), rs -> {
      ResultSetMetaData metadata = rs.getMetaData();
      int numColums = metadata.getColumnCount();

      List<Field> fields = ReflectionHelper.getInheritedFields(User.class);

      if (numColums != fields.size()) {
        throw new RuntimeException(
            "Number of columns in the ResultSet doesn't match the number of fields in the User object");
      }

      rs.next();
      User user = new User();

      for (Field field : fields) {
        String fieldName = field.getName();
        ReflectionHelper.setInheritedFieldValue(user, fieldName, rs.getObject(fieldName));
      }

      return user;
    });
  }

}
