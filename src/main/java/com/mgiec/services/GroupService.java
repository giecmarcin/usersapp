package com.mgiec.services;

import com.mgiec.dto.GroupAndUsers;
import com.mgiec.dto.UserAndGroups;
import com.mgiec.entities.Group;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin on 2017-02-17.
 */
public interface GroupService {
    void save(Group group);

    boolean edit(GroupAndUsers groupAndUsers);

    boolean remove(Long id);

    Optional<Group> findOne(Long id);

    List<Group> findAll();

    Optional<Group> findByName(String name);
}
