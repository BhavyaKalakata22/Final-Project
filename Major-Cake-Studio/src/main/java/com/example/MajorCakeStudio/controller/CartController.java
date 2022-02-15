
package com.example.MajorCakeStudio.controller;



import com.example.MajorCakeStudio.global.GlobalDat;
import com.example.MajorCakeStudio.model.MyOrder;
import com.example.MajorCakeStudio.model.Product;
import com.example.MajorCakeStudio.model.User;
import com.example.MajorCakeStudio.repository.UserRepository;
import com.example.MajorCakeStudio.service.OrderService;
import com.example.MajorCakeStudio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService orderService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable long id){

        GlobalDat.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cartGet(Model model){

        model.addAttribute("cartCount",GlobalDat.cart.size());
        model.addAttribute("total",GlobalDat.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",GlobalDat.cart);

        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){

        GlobalDat.cart.remove(index);
        return "redirect:/cart";
    }
    @RequestMapping("/myorders")
    public String showOrderHistory(Principal principal, Model model){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if (user.isPresent()){
            List<MyOrder> myOrders = orderService.listOrderHistory(user.get().getId());
            model.addAttribute("order", myOrders);
            return "myOrder";

        }else {
            return "redirect:/shop";
        }

    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalDat.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
    @RequestMapping("/payNow")
    public String payNow(Model model){
        System.out.println("Payment Success");
        return "checkout";
    }
}