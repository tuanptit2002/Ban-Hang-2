package com.example.quanqlibanhang.DTO;

import com.example.quanqlibanhang.entity.Bill;
import com.example.quanqlibanhang.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class BillItemsDTO {
    private Integer id;
    @JsonBackReference
    private BillDTO billDTO ;
    private ProductDTO productDTO;
    private int quality;
    private  double price;
}
