package com.mgiec.services;

import com.mgiec.entities.Authority;
import com.mgiec.entities.User;
import com.mgiec.repositories.AuthorityRepository;
import com.mgiec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class Init {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository authorityRepository;


    @PostConstruct
    void init() {

        Authority authorityAdmin  = authorityRepository.findByName("admin");
        if(authorityAdmin==null){
            authorityAdmin = new Authority("admin");
            authorityRepository.save(authorityAdmin);
        }
        Authority authorityUser  = authorityRepository.findByName("user");
        if(authorityUser==null){
            authorityUser = new Authority("user");
            authorityRepository.save(authorityUser);
        }


        Optional<User> user = userService.findByName("gcmarcin");
        if(!user.isPresent()){
            User admin = new User("gcmarcin","admin","Marcin", "Giec", LocalDate.now());
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authorityAdmin);
            authorities.add(authorityUser);
            admin.setAuthorities(authorities);
            userService.save(admin);
        }
    }
}
