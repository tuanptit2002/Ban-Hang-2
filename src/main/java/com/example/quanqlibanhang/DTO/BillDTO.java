package com.example.quanqlibanhang.DTO;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BillDTO {
    private Integer id;
    private UserDTO userDTO;

    private List<BillItemsDTO> billItemsDTOS = new ArrayList<>();
}
