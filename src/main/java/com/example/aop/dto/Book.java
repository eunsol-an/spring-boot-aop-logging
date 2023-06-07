package com.example.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String isbn;
    private int price;

    public void printThrowException() throws IllegalAccessException {
        System.out.print("Exception raised");
        throw new IllegalAccessException();
    }
}
