package com.example.quanqlibanhang.Controller;

import com.example.quanqlibanhang.DTO.BillDTO;
import com.example.quanqlibanhang.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    BillService billService ;
    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void create(@RequestBody BillDTO billDTO){
        billService.create(billDTO);
    }
    @PutMapping("/update")
    public void update(@ModelAttribute BillDTO billDTO){
        billService.update(billDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        billService.deleteById(id);
    }
    @GetMapping("/get/{id}")
    public BillDTO get(@PathVariable("id") int id){
       return billService.getBill(id);
    }

}
