package com.mgiec.repositories;

import com.mgiec.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface AuthorityRepository extends JpaRepository<Authority, Serializable> {
    Authority findByName(String name);
}
