package com.slasher.slasher.service.impl;

import com.slasher.slasher.entity.User;
import com.slasher.slasher.repository.UserRepository;
import com.slasher.slasher.repository.impl.UserRepositoryImpl;
import com.slasher.slasher.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

  UserRepository userRepository = new UserRepositoryImpl();

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User createUser(User user) {
    return null;
  }

  @Override
  public void deleteUserById(long id) {

  }

  @Override
  public User getUserById(long id) {
    return null;
  }

  @Override
  public List<User> getAllUsers() {
    return null;
  }
}
