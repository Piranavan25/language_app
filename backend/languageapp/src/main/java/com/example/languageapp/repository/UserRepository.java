package com.example.languageapp.repository;

import com.example.languageapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // later: findByName, findByLevel, etc.
}