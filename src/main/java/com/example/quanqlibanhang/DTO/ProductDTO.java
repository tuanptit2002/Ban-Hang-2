package com.example.quanqlibanhang.DTO;

import com.example.quanqlibanhang.entity.Category;
import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private double price;
    private String description;
    private String image;
    private CategoryDTO categoryDTO;
}
