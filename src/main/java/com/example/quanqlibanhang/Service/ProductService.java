package com.example.quanqlibanhang.Service;

import com.example.quanqlibanhang.DTO.CategoryDTO;
import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.DTO.ProductDTO;
import com.example.quanqlibanhang.Repo.CategoryRepo;
import com.example.quanqlibanhang.Repo.ProductRepo;
import com.example.quanqlibanhang.entity.Category;
import com.example.quanqlibanhang.entity.Product;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Transient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Transient
    public void create(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        Category category = categoryRepo.findById(productDTO.getCategoryDTO().getId()).orElseThrow(()-> new RuntimeException(""));
        product.setCategory(category);
        productRepo.save(product);
    }
    @Transient
    public void update(ProductDTO productDTO){
        Product product = productRepo.findById(productDTO.getId()).orElseThrow(NoResultException::new);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        Category category = categoryRepo.findById(productDTO.getCategoryDTO().getId()).orElseThrow(NoResultException::new);
        product.setCategory(category);
        productRepo.save(product);
    }
    @Transient
    public void deleteById(Integer id){
        categoryRepo.deleteById(id);
    }
    public PageDTO<ProductDTO>searchByPrice(double x ,double y ,int page ,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepo.searchByPrice(x,y,pageable);
        PageDTO<ProductDTO> productDTOS = new PageDTO<>();
        productDTOS.setTotalElements(productPage.getTotalElements());
        productDTOS.setTotalPages(productPage.getTotalPages());
        List<ProductDTO>list = new ArrayList<>();
        for(Product product: productPage.getContent()){
            list.add(new ModelMapper().map(product,ProductDTO.class));
        }
        productDTOS.setContents(list);
        return productDTOS;
    }
    public ProductDTO get(int id){
        Product product = productRepo.findById(id).orElseThrow(NoResultException::new);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());
        Category category = categoryRepo.findById(id).orElseThrow(NoResultException::new);
        CategoryDTO categoryDTO = new CategoryDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(category,categoryDTO);
        productDTO.setCategoryDTO(categoryDTO);
        return productDTO;
    }
}
