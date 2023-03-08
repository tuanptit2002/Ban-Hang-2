package com.example.quanqlibanhang.Service;

import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.DTO.UserDTO;
import com.example.quanqlibanhang.DTO.UserRolesDTO;
import com.example.quanqlibanhang.Repo.UserRepo;
import com.example.quanqlibanhang.Repo.UserRolesRepo;
import com.example.quanqlibanhang.entity.User;
import com.example.quanqlibanhang.entity.UserRoles;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserRolesRepo userRolesRepo;
    @Transient
    public void create(UserDTO userDTO){
        User user =new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        List<UserRolesDTO> useRoleDTO = userDTO.getUserRolesDTOS();
        for(UserRolesDTO useRoles : useRoleDTO) {
            if(useRoles.getRoles() != null) {
                UserRoles userRole = new UserRoles();
                userRole.setRoles(useRoles.getRoles());
                userRole.setUser(user);
                userRolesRepo.save(userRole);
            }
        }
        userRepo.save(user);
    }
    @Transient
    public void update(UserDTO userDTO){
        User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        userRepo.save(user);
    }
    @Transient
    public void updatePassword(UserDTO userDTO){
        User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
        user.setPassword(userDTO.getPassword());
        userRepo.save(user);
    }
    @Transient
    public void deleteById(int id){
        userRepo.deleteById(id);
    }

    public PageDTO<UserDTO> searchByName(String x , int page , int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepo.searchByName(x,pageable);
        PageDTO<UserDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(userPage.getTotalPages());
        pageDTO.setTotalElements(userPage.getTotalPages());
        List<UserDTO>list = new ArrayList<>();
        for(User user : userPage.getContent()){
            list.add(new ModelMapper().map(user,UserDTO.class));
        }
        pageDTO.setContents(list);
        return pageDTO;
    }
    public  UserDTO getUser(int id){
        User user = userRepo.findById(id).orElseThrow(NoResultException::new);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        List<UserRolesDTO> userRolesDTOS =new ArrayList<>();
        for(UserRoles userRoles : user.getUserRoles()){
            userRolesDTOS.add(new ModelMapper().map(userRoles,UserRolesDTO.class));
        }
        userDTO.setUserRolesDTOS(userRolesDTOS);
        return userDTO;
    }
}
