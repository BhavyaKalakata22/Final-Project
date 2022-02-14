package com.example.MajorCakeStudio.service;

import com.example.MajorCakeStudio.repository.CakeCartRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeCartService {

    @Autowired
    CakeCartRepositary cartRepository;
}