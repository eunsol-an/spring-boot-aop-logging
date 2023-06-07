package com.example.aop.dao;

import com.example.aop.dto.Book;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao {

    private List<Book> books;

    @PostConstruct
    private void init() {
        books = new ArrayList<>();
        Book book1 = new Book("spring","1488526510",30000);
        Book book2 = new Book("spring boot","1488751510",35000);
        Book book3 = new Book("boot spring boot","1481124536",25000);
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    public Book findBookByTitle(String title) {
        return books.stream().filter(book->book.getTitle().equals(title)).findFirst().orElse(new Book("","",0));
    }
}
