package com.mgiec.dto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class UserAndGroups {
    private Long id;
    @Valid
    private User user;
    private List<Long> idOfGroups = new ArrayList<>();

    public UserAndGroups() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getIdOfGroups() {
        return idOfGroups;
    }

    public void setIdOfGroups(List<Long> idOfGroups) {
        this.idOfGroups = idOfGroups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
