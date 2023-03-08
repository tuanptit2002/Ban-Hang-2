package com.example.quanqlibanhang.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String name;
    private double price;
    private String description;
    private String image;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

}
