package com.example.MajorCakeStudio.controller;


import com.example.MajorCakeStudio.dto.ProductDTO;
import com.example.MajorCakeStudio.model.Category;
import com.example.MajorCakeStudio.model.Product;
import com.example.MajorCakeStudio.service.CategoryService;
import com.example.MajorCakeStudio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    // admin section
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCategoriesAdd(Model model){

        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category) {

        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable long id){

        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable long id,Model model){

        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){

            model.addAttribute("category",category.get());
            return "categoriesAdd";
        }
        return "404";
    }

    // product section
    @GetMapping("/admin/products")
    public String products(Model model){

        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String productAddGet(Model model){

        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {

        Product product = new Product();

        // instead of below .. we can use map for setting for same variable names
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setWeight(productDTO.getWeight());

        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }
        else {
            imageUUID = imgName;
        }

        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model ){

        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO",productDTO);

        return "productsAdd";
    }

}
