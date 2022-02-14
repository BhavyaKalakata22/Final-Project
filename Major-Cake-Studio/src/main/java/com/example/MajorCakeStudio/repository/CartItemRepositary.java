package com.example.MajorCakeStudio.repository;

import com.example.MajorCakeStudio.model.CartItem;
import com.example.MajorCakeStudio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepositary extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUserId(Integer id);

}