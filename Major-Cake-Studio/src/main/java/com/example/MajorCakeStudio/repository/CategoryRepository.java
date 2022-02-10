package com.example.MajorCakeStudio.repository;


import com.example.MajorCakeStudio.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
