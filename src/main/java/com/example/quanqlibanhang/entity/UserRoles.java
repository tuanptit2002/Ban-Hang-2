package com.example.quanqlibanhang.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roles;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user = new User();
}