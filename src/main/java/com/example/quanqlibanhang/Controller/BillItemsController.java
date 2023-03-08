package com.example.quanqlibanhang.Controller;

import com.example.quanqlibanhang.DTO.BillItemsDTO;
import com.example.quanqlibanhang.Service.BillItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billitem")
public class BillItemsController {
    @Autowired
    BillItemsService billItemsService;
    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@ModelAttribute BillItemsDTO billItemsDTO){
        billItemsService.create(billItemsDTO);
    }
    @PutMapping("/update")
    public void update(@ModelAttribute BillItemsDTO billItemsDTO){
        billItemsService.update(billItemsDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")int id){
        billItemsService.deleteById(id);
    }
    @GetMapping("/get/{id}")
    public BillItemsDTO get(@PathVariable("id")int id){
        return billItemsService.get(id);
    }
}
