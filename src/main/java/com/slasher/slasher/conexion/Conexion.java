package com.slasher.slasher.conexion;

import io.vavr.control.Try;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

  private static Conexion conexion;

  public Conexion() {}

  public static Conexion getInstance() {
    if (conexion == null) {
      conexion = new Conexion();
    }
    return conexion;
  }

  public Connection getConnection() {
    return Try.of( () -> {
      Connection connection = null;
      Class.forName(Constant.DRIVER);

      connection = DriverManager.getConnection(Constant.URL+Constant.DB, Constant.USER, Constant.PASSWORD);
      System.out.println("conexion establecida");
      return connection;
    }).onFailure(Throwable::printStackTrace).get();
  }

}
