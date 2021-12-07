package com.slasher.slasher.repository;

import com.slasher.slasher.conexion.Conexion;
import com.slasher.slasher.entity.User;
import io.vavr.control.Try;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

  public User save(User user) {

    Try.of( () -> {
      PreparedStatement preparedStatement = Conexion.getInstance()
          .getConnection()
          .prepareStatement("INSERT INTO users(id_user, user_name, password) VALUES(?,?,?)");

      preparedStatement.setLong(1, user.getIdUser());
      preparedStatement.setString(2, user.getUserName());
      preparedStatement.setString( 3, user.getPassword());
      preparedStatement.executeUpdate();

      return user;
    }).onFailure(Throwable::printStackTrace);

    return user;
  }

  public Optional<List<User>> findAll() {
    return Optional.of(findAllUsersDo());
  }

  private List<User> findAllUsersDo() {
    return Try.of( () -> {
      List<User> users = new LinkedList<>();

      PreparedStatement statement = Conexion.getInstance()
          .getConnection()
          .prepareStatement("SELECT * FROM users");

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        User user = new User();

        user.setIdUser(resultSet.getLong(1));
        user.setUserName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));

        users.add(user);
      }

      return users;
    }).onFailure(Throwable::printStackTrace).get();
  }

}
