package com.example.MajorCakeStudio.service;

import com.example.MajorCakeStudio.model.Role;
import com.example.MajorCakeStudio.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
    public class RoleService {
        @Autowired
        RoleRepository roleRepository;
        private Role findRoleById(Integer id){
            return roleRepository.findById(id).get();
        }
    }

