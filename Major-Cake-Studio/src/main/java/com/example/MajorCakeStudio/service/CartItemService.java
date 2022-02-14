package com.example.MajorCakeStudio.service;

import com.example.MajorCakeStudio.model.CartItem;
import com.example.MajorCakeStudio.repository.CartItemRepositary;
import com.example.MajorCakeStudio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepositary cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> listCartItems(Integer id){
        return  cartItemRepository.findByUserId(id);
    }

    public CartItem addItemsToCart(CartItem cartItem){
        return  cartItemRepository.save(cartItem);
    }

}