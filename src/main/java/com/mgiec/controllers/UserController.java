package com.mgiec.controllers;

import com.mgiec.dto.UserAndGroups;
import com.mgiec.entities.User;
import com.mgiec.services.UserService;
import com.mgiec.services.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addOnlyUser(@Valid @RequestBody UserAndGroups userAndGroups, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrorBuilder.fromBindingErrors(errors));
        } else {
            User user = userService.saveWithEncryptedPassword(userAndGroups);
            if (Optional.ofNullable(user.getId()).isPresent()) {
                return ResponseEntity.ok(user);
            } else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUsers() {
        List<User> users = userService.findAll();
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findOneUser(@PathVariable Long id) {
        Optional<User> user = userService.findOne(id);
        if (user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<?> editUser(@RequestBody com.mgiec.dto.User user) {
        if (!userService.edit(user))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }

    @PostMapping(value = "/delete/id/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        if (userService.remove(id))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }
}
