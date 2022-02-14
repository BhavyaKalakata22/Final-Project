package com.example.MajorCakeStudio.repository;


import com.example.MajorCakeStudio.model.CakeCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeCartRepositary extends JpaRepository<CakeCart,Long> {
}