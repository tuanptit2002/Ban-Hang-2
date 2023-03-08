package com.example.quanqlibanhang.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BillItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Bill bill;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    private int quality;
    private  double price;
}
