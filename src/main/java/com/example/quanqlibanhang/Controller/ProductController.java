package com.example.quanqlibanhang.Controller;

import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.DTO.ProductDTO;
import com.example.quanqlibanhang.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")

    public  void create(@ModelAttribute ProductDTO productDTO){
        productService.create(productDTO);
    }
    @PutMapping("/update")

    public void update(@ModelAttribute ProductDTO productDTO){
        productService.update(productDTO);
    }
    @DeleteMapping("/delete/{id}")

    public void delete(@PathVariable("id")int id){
        productService.deleteById(id);
    }
    @GetMapping("/get/{id}")
    public ProductDTO get(@PathVariable("id")int id){
        return productService.get(id);
    }
    @GetMapping("/get/search")
    public PageDTO<ProductDTO> getPageDTO(@RequestParam(name="x",required = false)Integer x,
                                          @RequestParam(name="y",required = false)Integer y,
                                          @RequestParam(name="page",required = false)Integer page,
                                          @RequestParam(name="size",required = false)Integer size){
        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        x = x == null ? 0 :x;
        y = y == null ? 0 :y;
        return productService.searchByPrice(x,y,page,size);
    }

}
