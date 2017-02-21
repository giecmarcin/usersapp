package com.mgiec.services;

import com.mgiec.dto.GroupAndUsers;
import com.mgiec.entities.Group;
import com.mgiec.entities.User;
import com.mgiec.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Override
    public void save(Group group) {
        Optional<Group> optGroup = groupRepository.findByName(group.getName());
        if (!optGroup.isPresent())
            groupRepository.save(group);
    }

    @Override
    public boolean edit(GroupAndUsers groupAndUsers) {
        boolean isModified = true;
        Optional<Group> group = findOne(groupAndUsers.getIdOfGroup());

        if (group.isPresent()) {
            Group g = group.get();
            String oldName = g.getName();
            Set<User> newSelectedUsers = new HashSet<>();
            for (com.mgiec.dto.User u : groupAndUsers.getUsers()) {
                Optional<User> tempUser = userService.findOne(u.getId());
                if (tempUser.isPresent())
                    newSelectedUsers.add(tempUser.get());
            }
            for (User ur : g.getUsers()) {
                ur.getGroups().remove(g);
            }
            for (User ur : newSelectedUsers) {
                ur.getGroups().add(g);
            }
            //g.getUsers().retainAll(newSelectedUsers);
            g.setName(groupAndUsers.getName());
            Group modfiedGroup = groupRepository.edit(g);
            boolean objNotModified = newSelectedUsers.size() == modfiedGroup.getUsers().size() && g.getUsers().containsAll(newSelectedUsers) && oldName.equals(modfiedGroup.getName());
            if (objNotModified)
                isModified = false;
        }
        return isModified;
    }

    @Override
    public boolean remove(Long id) {
        //groupRepository.remove(id);
        boolean isModified = false;
        Optional<Group> group = groupRepository.findOne(id);
        if (group.isPresent()) {
            Group gr = group.get();
            for (User ur : gr.getUsers()) {
                ur.getGroups().remove(gr);
            }
            groupRepository.edit(gr);
            groupRepository.remove(id);
            Optional<Group> group2 = groupRepository.findOne(id);
            if (!group2.isPresent())
                isModified = true;
        }
        return isModified;
    }

    @Override
    public Optional<Group> findOne(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Group> findByName(String name) {
        return groupRepository.findByName(name);
    }
}
