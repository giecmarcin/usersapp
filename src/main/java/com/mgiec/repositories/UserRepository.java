package com.mgiec.repositories;

import com.mgiec.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    User edit(com.mgiec.entities.User user);

    void remove(Long id);

    Optional<User> findOne(Long id);

    List<User> findAll();

    Optional<User> findByName(String name);
}
