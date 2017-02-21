package com.mgiec.controllers;

import com.mgiec.dto.GroupAndUsers;
import com.mgiec.dto.User;
import com.mgiec.dto.UserAndGroups;
import com.mgiec.entities.Group;
import com.mgiec.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<?> addOnlyUser(@RequestBody String nameOfGroup) {
        Group group = new Group(nameOfGroup);
        groupService.save(group);
        if (Optional.ofNullable(group.getId()).isPresent())
            return ResponseEntity.ok(group);
        else
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUsers() {
        List<Group> groups = groupService.findAll();
        if (!groups.isEmpty())
            return ResponseEntity.ok(groups);
        else
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findOneGroup(@PathVariable Long id) {
        Optional<Group> group = groupService.findOne(id);
        if (group.isPresent())
            return ResponseEntity.ok(group.get());
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<?> editGroup(@RequestBody GroupAndUsers groupAndUsers) {
        if (groupService.edit(groupAndUsers))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }

    @PostMapping(value = "/delete/id/{id}")
    public ResponseEntity<?> removeGroup(@PathVariable Long id) {
        if (groupService.remove(id))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }
}
