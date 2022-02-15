package com.example.MajorCakeStudio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


}
