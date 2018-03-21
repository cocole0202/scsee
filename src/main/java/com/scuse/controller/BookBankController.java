package com.scuse.controller;


import com.scuse.dto.Result;
import com.scuse.entity.BookBank;
import com.scuse.service.BookBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sheng Junyi on 2018/3/20
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/bookbank")
public class BookBankController {

    @Autowired
    BookBankService bookBankService;

      /*
    * 添加BookBank
    * @param BookBanks: 要添加的库存
    * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addBookBank(@RequestBody List<BookBank> bookBanks){return bookBankService.addBookBank(bookBanks);}

    /*
   * 删除BookBank
   * @param : 要删除的库存的id列表
   * @return 返回删除操作错误信息
    */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delBookBank(@RequestBody List<Integer>ids){return bookBankService.delBookBank(ids);}

     /*
   * 更新BookBank
   * @param BookBank: BookBank列表
   * @return 返回更新操作错误信息
    */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updBookBank(@RequestBody List<BookBank>bookBanks){return bookBankService.updBookBank(bookBanks);}

    /*
   * 获取BookBank
   * @return 返回操作错误信息以及BookBank列表
    */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getBookBank(){return bookBankService.getBookBank();}
}
