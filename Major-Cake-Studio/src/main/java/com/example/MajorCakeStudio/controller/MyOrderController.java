/*
package com.example.MajorCakeStudio.controller;

import com.example.MajorCakeStudio.model.CustomUserDetail;
import com.example.MajorCakeStudio.model.User;
import com.example.MajorCakeStudio.repository.UserRepository;
import com.example.MajorCakeStudio.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MyOrderController {
        @Autowired
        UserRepository userRepository;
@GetMapping("/myOrder")
public String myOrder(Principal principal, Model model){
        if (principal != null) {
        String username = principal.getName();
        User user = userRepository.findUserByEmail();
        model.addAttribute("username", user.getFirstName());
        }
        model.addAttribute("orders",myOrderService.myOrders(principal.getName()));
        return "myOrder";
        }

@GetMapping("/order/{id}")
public String cancelOrder(@PathVariable Long id,Model model, Principal principal){
        if (principal != null) {
        String username = principal.getName();
        Customer customer = customerService.getByUsername(username);
        model.addAttribute("username", customer.getName());
        }
        myOrderService.remove(id);
        return "redirect:/myOrder";
        }

        }
*/
