package com.example.quanqlibanhang.Service;

import com.example.quanqlibanhang.DTO.BillDTO;
import com.example.quanqlibanhang.DTO.BillItemsDTO;
import com.example.quanqlibanhang.DTO.ProductDTO;
import com.example.quanqlibanhang.Repo.BillItemsRepo;
import com.example.quanqlibanhang.Repo.BillRepo;
import com.example.quanqlibanhang.Repo.ProductRepo;
import com.example.quanqlibanhang.entity.Bill;
import com.example.quanqlibanhang.entity.BillItems;
import com.example.quanqlibanhang.entity.Product;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillItemsService {
    @Autowired
    BillItemsRepo billItemsRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    BillRepo billRepo;
    @Transient
    public void create(BillItemsDTO billItemsDTO){
        BillItems billItems = new BillItems();
        billItems.setPrice(billItems.getPrice());
        billItems.setQuality(billItemsDTO.getQuality());
        Product product = productRepo.findById(billItemsDTO.getProductDTO().getId()).orElseThrow(NoResultException::new);
        billItems.setProduct(product);
        Bill bill = billRepo.findById(billItemsDTO.getBillDTO().getId()).orElseThrow(NoResultException::new);
        billItems.setBill(bill);
        billItemsRepo.save(billItems);
    }
    @Transient
    public void update(BillItemsDTO billItemsDTO){
        BillItems billItems = billItemsRepo.findById(billItemsDTO.getId()).orElseThrow(NoResultException::new);
        billItems.setPrice(billItems.getPrice());
        billItems.setQuality(billItemsDTO.getQuality());
        Product product = productRepo.findById(billItemsDTO.getProductDTO().getId()).orElseThrow(NoResultException::new);
        billItems.setProduct(product);
        Bill bill = billRepo.findById(billItemsDTO.getBillDTO().getId()).orElseThrow(NoResultException::new);
        billItems.setBill(bill);
        billItemsRepo.save(billItems);
    }
    @Transient
    public void deleteById(int id){
        billItemsRepo.deleteById(id);
    }
    public BillItemsDTO get(int id){
        BillItems billItems = billItemsRepo.findById(id).orElseThrow(NoResultException::new);
        BillItemsDTO billItemsDTO = new BillItemsDTO();
        billItemsDTO.setQuality(billItems.getQuality());
        billItemsDTO.setPrice(billItems.getPrice());
        Product product = productRepo.findById(id).orElseThrow(NoResultException::new);//chua  chac dung ;
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = new ProductDTO();
        modelMapper.map(product,productDTO);
        billItemsDTO.setProductDTO(productDTO);
        Bill bill = billRepo.findById(id).orElseThrow(NoResultException::new);
        BillDTO billDTO = new BillDTO();
        modelMapper.map(bill,billDTO);
        billItemsDTO.setBillDTO(billDTO);
        return billItemsDTO;
    }
}

