package com.slasher.slasher.service;

import com.slasher.slasher.entity.User;

import java.util.List;

public interface UserService {
  User createUser(User user);
  void deleteUserById(long id);
  User getUserById(long id);
  List<User> getAllUsers();
}
