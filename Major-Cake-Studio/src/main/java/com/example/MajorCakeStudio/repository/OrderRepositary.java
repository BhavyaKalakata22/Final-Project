package com.example.MajorCakeStudio.repository;


import com.example.MajorCakeStudio.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositary extends JpaRepository<MyOrder,Long> {
    List<MyOrder> findByUserId(Integer id);
}

