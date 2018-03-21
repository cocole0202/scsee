package com.scuse;

import com.scuse.dto.Result;
import com.scuse.entity.Book;
import com.scuse.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    public void addBook(){
       List<Book>books=new ArrayList<Book>();
       Book book=new Book();
       book.setName("高等数学");
       book.setId(15);
       book.setIsbn(1234);
       books.add(book);
       com.scuse.dto.Result result=bookService.addBook(books);
       System.out.println(result.getError().getMessage());
    }

    @Test
    public void delBook() throws Exception {
        List<Integer>ids = new ArrayList<Integer>();
        ids.add(1);
        Result result=bookService.delBook(ids);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void updBook() throws Exception {
        List<Book>books=new ArrayList<Book>();
        Book book=new Book();
        book.setId(1);
        book.setName("数学");
        books.add(book);
        Result result=bookService.updBook(books);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void getBook() throws Exception {
    }

}