package com.mgiec.repositories;

import com.mgiec.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface TokenRepository extends JpaRepository<Token, Serializable> {
}
