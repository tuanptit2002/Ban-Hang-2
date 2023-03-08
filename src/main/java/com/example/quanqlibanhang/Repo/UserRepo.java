package com.example.quanqlibanhang.Repo;

import com.example.quanqlibanhang.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User , Integer> {
    @Query("SELECT u from User u where u.name like :x")
    Page<User>searchByName(@Param("x")String x, Pageable pageable);

}
