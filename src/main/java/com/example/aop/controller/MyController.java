package com.example.aop.controller;

import com.example.aop.service.BookService;
import com.example.aop.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {
    private final BookService bookService;
    private final HelloService helloService;

    @GetMapping("/")
    public String index(@RequestParam String message) {
        bookService.findBookByTitle("spring");
        return "indexPage " + message;
    }
    @GetMapping("/hello")
    public String hello() {
        helloService.hello();
        return "hello";
    }
}

