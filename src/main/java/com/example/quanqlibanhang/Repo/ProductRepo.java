package com.example.quanqlibanhang.Repo;

import com.example.quanqlibanhang.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query("select p FROM Product p where p.price BETWEEN :x and :y")
    Page<Product>searchByPrice(@Param("x")double x , @Param("y")double y , Pageable pageable);
}
