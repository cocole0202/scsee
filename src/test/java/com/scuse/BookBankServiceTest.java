package com.scuse;

import com.scuse.entity.BookBank;
import com.scuse.service.BookBankService;
import com.scuse.dto.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookBankServiceTest {

    @Autowired
    BookBankService bookBankService;

    @Test
    public void addBookBank() throws Exception {
        List<BookBank>bookBanks = new ArrayList<BookBank>();
        BookBank bookBank=new BookBank();
        bookBank.setId(2);
        bookBank.setBookId(2123);
        bookBank.setNumber(222);
        bookBanks.add(bookBank);
        com.scuse.dto.Result result =bookBankService.addBookBank(bookBanks);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void delBookBank() throws Exception {
       List<Integer> ids = new ArrayList<Integer>();
       ids.add(2);
        com.scuse.dto.Result result=bookBankService.delBookBank(ids);
       System.out.println(result.getError().getMessage());
    }

    @Test
    public void updBookBank() throws Exception {
        List<BookBank>bookBanks = new ArrayList<BookBank>();
        BookBank bookBank=new BookBank();
        bookBank.setId(1);
        bookBank.setBookId(555);
        bookBank.setNumber(555);
        bookBanks.add(bookBank);
        com.scuse.dto.Result result =bookBankService.updBookBank(bookBanks);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void getBookBank() throws Exception {
    }

}