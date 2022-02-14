package com.example.MajorCakeStudio.repository;

import com.example.MajorCakeStudio.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositary extends JpaRepository<MyOrder,Long> {
}
