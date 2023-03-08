package com.example.quanqlibanhang.Service;

import com.example.quanqlibanhang.DTO.CategoryDTO;
import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.Repo.CategoryRepo;
import com.example.quanqlibanhang.entity.Category;

import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Transient
    public void create(CategoryDTO categoryDTO){
       Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepo.save(category);
    }
    @Transient
    public void update(CategoryDTO categoryDTO){
        Category category = categoryRepo.findById(categoryDTO.getId()).orElseThrow(NoResultException::new);
        category.setName(categoryDTO.getName());
        categoryRepo.save(category);
    }
    @Transient
    public void deleteById(int id){
        categoryRepo.deleteById(id);
    }
    public PageDTO<CategoryDTO>searchByName(String x,int page ,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Category> categories = categoryRepo.searchByName(x,pageable);
        PageDTO<CategoryDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(categories.getTotalPages());
        pageDTO.setTotalElements(categories.getTotalElements());
        List<CategoryDTO> list = new ArrayList<>();
        for(Category category : categories.getContent()){
            list.add(new ModelMapper().map(category,CategoryDTO.class));
        }
        pageDTO.setContents(list);
        return pageDTO;
    }
    public CategoryDTO get(int id){
        Category category = categoryRepo.findById(id).orElseThrow(NoResultException::new);
        CategoryDTO categoryDTO = new CategoryDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(category,categoryDTO);
        return categoryDTO;
    }
}
