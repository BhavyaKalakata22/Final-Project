package com.example.MajorCakeStudio.repository;


import com.example.MajorCakeStudio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByEmail(String email);
}
