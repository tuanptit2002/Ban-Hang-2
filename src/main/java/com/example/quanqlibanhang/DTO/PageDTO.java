package com.example.quanqlibanhang.DTO;

import lombok.Data;

import java.util.List;
@Data
public class PageDTO <T>{
    private int totalPages;
    private long totalElements;
    private List<T> contents;
}
