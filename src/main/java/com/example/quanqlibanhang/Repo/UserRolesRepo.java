package com.example.quanqlibanhang.Repo;

import com.example.quanqlibanhang.entity.UserRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRolesRepo extends JpaRepository<UserRoles,Integer> {
    @Query("SELECT ur FROM UserRoles ur where ur.roles = :x")
    Page<UserRoles>searchByRoles(@Param("x")String x, Pageable pageable);
}
