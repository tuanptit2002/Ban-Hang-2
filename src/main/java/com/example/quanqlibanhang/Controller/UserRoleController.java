package com.example.quanqlibanhang.Controller;

import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.DTO.UserRolesDTO;
import com.example.quanqlibanhang.Service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;

@RestController
@RequestMapping("/api/userrole")
public class UserRoleController {
    @Autowired
    UserRolesService userRolesService;
    @PostMapping("/new")

    @PreAuthorize("hasRole('ADMIN')")
    public void create(UserRolesDTO userRolesDTO){
        userRolesService.create(userRolesDTO);
    }

    @PutMapping("/update")


    public void update(UserRolesDTO userRolesDTO){
        userRolesService.update(userRolesDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        userRolesService.deleteById(id);
    }
    @GetMapping("get/{id}")
    public UserRolesDTO get(@PathVariable("id")int id){
        return userRolesService.getUserRole(id);
    }
    @GetMapping("/get")
    public PageDTO<UserRolesDTO>getByRole(@RequestParam(name="name",required = false)String name,
                                          @RequestParam(name="page",required = false)Integer page,
                                          @RequestParam(name="size",required = false)Integer size){
        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        name = name == null ? "" :name;
       return userRolesService.searchByRoles(name,page,size);
    }
}
