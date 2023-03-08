package com.example.quanqlibanhang.Service;

import com.example.quanqlibanhang.DTO.BillDTO;
import com.example.quanqlibanhang.DTO.BillItemsDTO;
import com.example.quanqlibanhang.DTO.UserDTO;
import com.example.quanqlibanhang.Repo.BillItemsRepo;
import com.example.quanqlibanhang.Repo.BillRepo;
import com.example.quanqlibanhang.Repo.UserRepo;
import com.example.quanqlibanhang.entity.Bill;
import com.example.quanqlibanhang.entity.BillItems;
import com.example.quanqlibanhang.entity.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Transient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepo billRepo ;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BillItemsRepo billItemsRepo;
    @Autowired
    BillItemsService billItemsService;
    @Transient
    public void create(BillDTO billDTO){
        Bill bill = new Bill();
        User user = userRepo.findById(billDTO.getUserDTO().getId()).orElseThrow(NoResultException::new);
        List<BillItems> billItems = new ArrayList<>();
        bill = billRepo.save(bill);
        for(BillItemsDTO billItemsDTO : billDTO.getBillItemsDTOS()){
                billDTO.setId(bill.getId());
                billItemsDTO.setBillDTO(billDTO);

                billItemsService.create(billItemsDTO);
        }
        bill.setBillItems(billItems);
        bill.setUser(user);
        billRepo.save(bill);
    }
    @Transient
    public void update(BillDTO billDTO){
        Bill bill = billRepo.findById(billDTO.getId()).orElseThrow(NoResultException::new);

        User user = userRepo.findById(billDTO.getUserDTO().getId()).orElseThrow(NoResultException::new);
        bill.setUser(user);
        List<BillItems>billItems = new ArrayList<>();
        for(BillItemsDTO billItemsDTO : billDTO.getBillItemsDTOS()){
            BillItems billItems1 = billItemsRepo.findById(billItemsDTO.getId()).orElseThrow(NoResultException::new);
            // chua dung ti lam lai
            billItems.add(billItems1);
        }
        bill.setBillItems(billItems);
        billRepo.save(bill);

    }
    @Transient
    public void deleteById(Integer id){
        billRepo.deleteById(id);
    }
    public BillDTO getBill(int id){
        Bill bill = billRepo.findById(id).orElseThrow(NoResultException::new);
        User user = bill.getUser();
        BillDTO billDTO = new BillDTO();
        UserDTO userDTO = new UserDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user,userDTO);
        billDTO.setUserDTO(userDTO);
        List<BillItemsDTO>billItemsDTOS = new ArrayList<>();
        List<BillItems> billItems = bill.getBillItems();
        for(BillItems billItems1 : billItems){
            billItemsDTOS.add(new ModelMapper().map(billItems1,BillItemsDTO.class));
        }
        billDTO.setBillItemsDTOS(billItemsDTOS);
        return billDTO;
    }

}
