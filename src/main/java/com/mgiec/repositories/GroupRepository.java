package com.mgiec.repositories;

import com.mgiec.entities.Group;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin on 2017-02-17.
 */
public interface GroupRepository {
    void save(Group group);

    Group edit(Group group);

    void remove(Long id);

    Optional<Group> findOne(Long id);

    List<Group> findAll();

    Optional<Group> findByName(String name);
}
