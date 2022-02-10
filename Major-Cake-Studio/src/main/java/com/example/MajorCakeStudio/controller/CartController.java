
package com.example.MajorCakeStudio.controller;



import com.example.MajorCakeStudio.global.GlobalDat;
import com.example.MajorCakeStudio.model.Product;
import com.example.MajorCakeStudio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

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

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalDat.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
}