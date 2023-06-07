package com.example.aop.service;

import com.example.aop.dao.BookDao;
import com.example.aop.dto.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;

    public Book findBookByTitle(String title) {
        return bookDao.findBookByTitle(title);
    }
}
