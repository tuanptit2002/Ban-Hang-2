package com.example.quanqlibanhang.Service;

import com.example.quanqlibanhang.DTO.PageDTO;
import com.example.quanqlibanhang.DTO.UserDTO;
import com.example.quanqlibanhang.DTO.UserRolesDTO;
import com.example.quanqlibanhang.Repo.UserRepo;
import com.example.quanqlibanhang.Repo.UserRolesRepo;
import com.example.quanqlibanhang.entity.User;
import com.example.quanqlibanhang.entity.UserRoles;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Transient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRolesService {
    @Autowired
    UserRolesRepo userRolesRepo;
    @Autowired
    UserRepo userRepo;
    @Transient
    public void create(UserRolesDTO userRolesDTO){
        UserRoles userRoles = new UserRoles();
        userRoles.setRoles(userRolesDTO.getRoles());
        User user = userRepo.findById(userRolesDTO.getUserId()).orElseThrow(NoResultException::new);
        userRoles.setUser(user);
        userRolesRepo.save(userRoles);
    }
    @Transient
    public void update(UserRolesDTO userRolesDTO){
        UserRoles userRoles = userRolesRepo.findById(userRolesDTO.getId()).orElseThrow(NoResultException::new);
        userRoles.setRoles(userRolesDTO.getRoles());
        User user = userRepo.findById(userRolesDTO.getUserId()).orElseThrow(NoResultException::new);
        userRoles.setUser(user);
        userRolesRepo.save(userRoles);
    }
    @Transient
    public void deleteById(int id){
        userRolesRepo.deleteById(id);
    }
    public PageDTO<UserRolesDTO>searchByRoles(String s,int page ,int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<UserRoles>userRoles = userRolesRepo.searchByRoles(s,pageable);
        PageDTO<UserRolesDTO> userRolesDTOPageDTO = new PageDTO<>();
        userRolesDTOPageDTO.setTotalPages(userRoles.getTotalPages());
        userRolesDTOPageDTO.setTotalElements(userRoles.getTotalElements());
        List<UserRolesDTO> list = new ArrayList<>();
        for(UserRoles userRoles1 : userRoles.getContent()){
            list.add(new ModelMapper().map(userRoles1,UserRolesDTO.class));
        }
        return userRolesDTOPageDTO;
    }
    public UserRolesDTO getUserRole(int id){
        UserRoles userRoles = userRolesRepo.findById(id).orElseThrow(NoResultException::new);
        UserRolesDTO userRolesDTO = new UserRolesDTO();
        userRolesDTO.setRoles(userRoles.getRoles());
        return  userRolesDTO;
    }
}
