package com.example.MajorCakeStudio.repository;
import com.example.MajorCakeStudio.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
     List<Product> findByCategoryId(long id);
}