package com.example.aop;

import com.example.aop.dto.Book;
import com.example.aop.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) throws IllegalAccessException {
//		SpringApplication.run(AopApplication.class, args);

		ConfigurableApplicationContext ac = SpringApplication.run(AopApplication.class, args);

		//Fetching the account object from the application context
//		BookService bookService = ac.getBean("bookService", BookService.class);
//		Book book;
//
//		try {
//			//generating exception
//			book = bookService.findBookByTitle(null);
//			if (book != null)
//				System.out.println(book.getTitle() + "\t" + book.getIsbn() + "\t" + book.getPrice());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		Book book = (Book) ac.getBean("book");
		book.printThrowException();
	}

}
