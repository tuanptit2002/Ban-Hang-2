package com.example.quanqlibanhang.DTO;

import com.example.quanqlibanhang.entity.UserRoles;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private List<UserRolesDTO> userRolesDTOS = new ArrayList<>();
}
