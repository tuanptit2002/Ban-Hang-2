package com.example.quanqlibanhang.Repo;

import com.example.quanqlibanhang.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    @Query("SELECT c FROM Category c where c.name like :x")
    Page<Category>searchByName(@Param("x")String x , Pageable pageable);
}
