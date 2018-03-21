package com.scuse.service;


import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Book;
import com.scuse.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sheng Junyi on 2018/3/20
 *
 * Major 相关表操作
 */

@Repository
public class BookService {
    @Autowired
    BookMapper bookMapper;

    /*
    * 添加Book
    * @param books: book列表
    * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result addBook(List<Book>books){
        try {
            for (Book book: books){
                bookMapper.insertSelective(book);
            }
        }catch (Exception e){
            return OpResult.ADD_ERROR;
        }
        return  OpResult.ADD_SUCCESS;
    }

    /*
    * 删除Book
    * @param ids : 需要删除的books的id列表
    * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delBook(List<Integer> ids){
        try{
            for(Integer id: ids){
                bookMapper.deleteByPrimaryKey(id);
            }
        }catch(Exception e){
            return OpResult.DEL_ERROR;
        }
        return  OpResult.DEL_SUCCESS;
    }
    /*
  * 更新Book
  * @param books : book列表
  * @return 返回更新操作错误信息
   */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updBook(List<Book> books){
        try{
            for (Book book:books){
                bookMapper.updateByPrimaryKeySelective(book);
            }
        }catch (Exception e){
            return  OpResult.UPD_ERROR;
        }
        return OpResult.UPD_SUCCESS;
    }

    /*
   * 获取Book
   * @return 返回操作错误信息以及Book列表
    */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getBook(){
        try{
            List<Book> books=bookMapper.getAll();
            Result<List<Book>> result= new Result<>();
            result.setError(new Error(0,"获取专业信息成功"));
            result.setData(books);
            return result;
        }catch (Exception e){
            return  OpResult.GET_ERROR;
        }
    }
}
