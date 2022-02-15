package com.example.MajorCakeStudio.service;
import com.example.MajorCakeStudio.model.MyOrder;
import com.example.MajorCakeStudio.repository.OrderRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepositary orderRepository;

    public MyOrder addOrderDetails(MyOrder order){

        return orderRepository.save(order);
    }
    public List<MyOrder> listOrderHistory(Integer id){

        return  orderRepository.findByUserId(id);
    }
}
