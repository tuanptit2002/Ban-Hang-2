package com.example.quanqlibanhang.Controller;

import com.example.quanqlibanhang.DTO.CategoryDTO;
import com.example.quanqlibanhang.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public void create(CategoryDTO categoryDTO){
        categoryService.create(categoryDTO);
    }
    @PutMapping("/update")
    public void update(CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
    }
    @GetMapping("/get/{id}")
    public CategoryDTO get(@PathVariable("id") int id){
        return categoryService.get(id);
    }
}
