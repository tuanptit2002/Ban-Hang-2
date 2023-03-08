package com.example.quanqlibanhang.Controller;

import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.DTO.UserDTO;
import com.example.quanqlibanhang.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@ModelAttribute UserDTO userDTO){
        userService.create(userDTO);
    }
    @PutMapping("/update")
    public void update(@ModelAttribute UserDTO userDTO){
        userService.update(userDTO);
    }
    @PutMapping("/update/password")
    public void updatePassword(@ModelAttribute UserDTO userDTO){
        userService.updatePassword(userDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id){
        userService.deleteById(id);
    }
    @GetMapping("/get/{id}")
    public UserDTO getUser(@PathVariable("id") int id){
        return userService.getUser(id);
    }
    @GetMapping("/get/search")
    public PageDTO<UserDTO> search( @RequestParam(name="name",required = false)String name,
                                    @RequestParam(name="page",required = false)Integer page,
                                    @RequestParam(name="size",required = false)Integer size){
        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        name = name == null ? "" :name;
        return userService.searchByName(name,page,size);
    }
}
