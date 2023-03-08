package com.example.quanqlibanhang.Repo;

import com.example.quanqlibanhang.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill,Integer> {
}
