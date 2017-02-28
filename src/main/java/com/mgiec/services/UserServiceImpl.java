package com.mgiec.services;

import com.mgiec.dto.UserAndGroups;
import com.mgiec.entities.Group;
import com.mgiec.entities.User;
import com.mgiec.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Override
    public User saveWithEncryptedPassword(UserAndGroups userAndGroups) {
        com.mgiec.entities.User user = createUserEntityFromDto(userAndGroups);
        Optional<User> optUser = userRepository.findByName(userAndGroups.getUser().getName());
        if (optUser.isPresent())
            return user;
        else {
            byte[] encryptedPassword = DigestUtils.sha1(user.getPassword());
            user.setPassword(Converter.base64Encode(encryptedPassword));
            addGroupsToUser(user, userAndGroups.getIdOfGroups());
            userRepository.save(user);
            return user;
        }
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean edit(com.mgiec.dto.User user) {
        boolean isModified = false;
        Optional<User> optUser = findOne(user.getId());

        String oldName = optUser.get().getName();
        String oldFirstName = optUser.get().getFirstName();
        String oldLastName = optUser.get().getLastName();
        LocalDate oldDate = optUser.get().getDateOfBirth();
        com.mgiec.entities.User oldUser = new User(oldName, oldFirstName, oldLastName, oldDate);
        if (optUser.isPresent()) {
            User u = optUser.get();
            u.setName(user.getName());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setDateOfBirth(user.getDateOfBirth());
            com.mgiec.entities.User modifiedUser = userRepository.edit(u);
            isModified = oldUser.equals(modifiedUser);
        }
        return isModified;
    }

    @Override
    public boolean remove(Long id) {
        userRepository.remove(id);
        Optional<User> user = userRepository.findOne(id);
        if (user.isPresent())
            return false;
        else
            return true;
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void addGroupsToUser(User user, List<Long> groupIds) {
        for (Long id : groupIds) {
            Optional<Group> group = groupService.findOne(id);
            if (group.isPresent()) {
                user.getGroups().add(group.get());
            }
        }
    }

    public com.mgiec.entities.User createUserEntityFromDto(UserAndGroups userAndGroups) {
        return new User(userAndGroups.getUser().getName(), userAndGroups.getUser().getPassword(), userAndGroups.getUser().getFirstName(), userAndGroups.getUser().getLastName(), userAndGroups.getUser().getDateOfBirth());
    }
}
