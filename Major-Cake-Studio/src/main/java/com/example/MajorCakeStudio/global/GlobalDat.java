package com.example.MajorCakeStudio.global;
import com.example.MajorCakeStudio.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalDat {

    public static List<Product> cart;

    static {
        cart = new ArrayList<Product>();
    }

}
