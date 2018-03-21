package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Book;
import com.scuse.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sheng Junyi on 2018/3/20
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    /*
    * 添加Book
    * @param books: 要添加的教材
    * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addBook(@RequestBody List<Book>books){return bookService.addBook(books);}

    /*
   * 删除Book
   * @param : 要删除的教材的id列表
   * @return 返回删除操作错误信息
    */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delBook(@RequestBody List<Integer> ids){ return bookService.delBook(ids);}

    /*
   * 更新Book
   * @param books: book列表
   * @return 返回更新操作错误信息
    */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updBook(@RequestBody List<Book>books){return bookService.updBook(books);}

    /*
   * 获取Book
   * @return 返回操作错误信息以及book列表
    */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getBook(){return bookService.getBook();}
}

