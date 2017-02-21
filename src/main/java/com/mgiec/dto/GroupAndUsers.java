package com.mgiec.dto;


import java.util.List;

public class GroupAndUsers {
    private Long idOfGroup;
    private String name;
    private List<User> users;

    public GroupAndUsers() {
    }

    public String getName() {
        return name;
    }

    public Long getIdOfGroup() {
        return idOfGroup;
    }

    public void setIdOfGroup(Long idOfGroup) {
        this.idOfGroup = idOfGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
