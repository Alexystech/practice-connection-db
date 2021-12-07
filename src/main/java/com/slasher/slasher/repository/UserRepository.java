package com.slasher.slasher.repository;

import com.slasher.slasher.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  User save(User user);
  void deleteById(long id);
  Optional<User> findById(long id);
  Optional<List<User>> findAll();
}
