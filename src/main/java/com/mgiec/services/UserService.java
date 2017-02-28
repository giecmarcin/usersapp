package com.mgiec.services;

import com.mgiec.dto.UserAndGroups;
import com.mgiec.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveWithEncryptedPassword(UserAndGroups userAndGroups);

    void save(User user);

    boolean edit(com.mgiec.dto.User user);

    boolean remove(Long id);

    Optional<User> findOne(Long id);

    List<User> findAll();

    Optional<User> findByName(String name);
}
