package com.slasher.slasher.repository.impl;

import com.slasher.slasher.conexion.Conexion;
import com.slasher.slasher.entity.User;
import com.slasher.slasher.repository.UserRepository;
import io.vavr.control.Try;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

  @Override
  public User save(User user) {

    Try.of( () -> {
      PreparedStatement preparedStatement = Conexion.getInstance()
          .getConnection()
          .prepareStatement("INSERT INTO users(id_user, user_name, password) VALUES(?,?,?)");

      preparedStatement.setLong(1, user.getIdUser());
      preparedStatement.setString(2, user.getUserName());
      preparedStatement.setString( 3, user.getPassword());
      preparedStatement.executeUpdate();

      preparedStatement.close();
      return user;
    }).onFailure(Throwable::printStackTrace);

    return user;
  }

  @Override
  public void deleteById(long id) {
    Try.of( () -> {
      PreparedStatement preparedStatement = Conexion.getInstance()
          .getConnection()
          .prepareStatement("DELETE FROM users WHERE id_user = ?");

      preparedStatement.setLong(1, id);
      preparedStatement.executeUpdate();

      preparedStatement.close();
      return true;
    }).onFailure(Throwable::printStackTrace);
  }

  @Override
  public Optional<User> findById(long id) {
    return Optional.of(findUserByIdDo(id));
  }

  @Override
  public Optional<List<User>> findAll() {
    return Optional.of(findAllUsersDo());
  }

  private User findUserByIdDo(long id) {
    return Try.of( () -> {
      User user = new User();
      PreparedStatement preparedStatement = Conexion.getInstance()
          .getConnection()
          .prepareStatement("SELECT * FROM users WHERE id_user = ?");

      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        user.setIdUser(resultSet.getLong(1));
        user.setUserName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
      }

      return user;
    }).onFailure(Throwable::printStackTrace).get();
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

      statement.close();
      resultSet.close();
      return users;
    }).onFailure(Throwable::printStackTrace).get();
  }

}
