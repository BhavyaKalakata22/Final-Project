
package com.example.MajorCakeStudio.controller;


//import com.example.MajorCakeStudio.global.GlobalDat;
import com.example.MajorCakeStudio.model.Role;
import com.example.MajorCakeStudio.model.User;
import com.example.MajorCakeStudio.repository.RoleRepository;
import com.example.MajorCakeStudio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){

        // clearing cart when user clicks login
       // GlobalDat.cart.clear();
        return "login";
    }
    @GetMapping("/success")
    public String login(Principal principal){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if(user.get().getEmail().contains("admin")){
            return "redirect:/admin";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String registerGet(){

        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {

        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());

        user.setRoles(roles);
        userRepository.save(user);

        request.login(user.getEmail(),password);

        return "redirect:/";

    }
    @RequestMapping("/userProfile")
    public String showUserProfile() {
        return "userProfile";
    }


    @GetMapping("/updateProfile")
    public String updateUser(){
        return "updateProfile";
    }

    @RequestMapping("/forgotpassword")
      public String forgetpass()
        {
    return "forgotpassword";
        }

   /* @GetMapping("/error")
    public String errorInLogin(){

        return "errorPage";
    }*/
}
