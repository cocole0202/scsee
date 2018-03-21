package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.BookBank;
import com.scuse.entity.Major;
import com.scuse.mapper.BookBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Sheng Junyi on 2018/3/20
 *
 * BookBank 相关表操作
 */
@Repository
public class BookBankService {

    @Autowired
    BookBankMapper bookBankMapper;

    /*
    * 添加BookBank
    * @param bookBanks: bookBank列表
    * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result addBookBank(List<BookBank> bookBanks){
        try{
            for (BookBank bookBank: bookBanks){
                bookBankMapper.insertSelective(bookBank);
            }
        }catch (Exception e){
            return OpResult.ADD_ERROR;
        }
        return  OpResult.ADD_SUCCESS;
    }

    /*
    * 删除BookBank
    * @param ids : 需要删除的majores的id列表
    * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delBookBank(List<Integer>ids){
        try {
            for (Integer id : ids)
                bookBankMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            return OpResult.DEL_ERROR;
        }
        return OpResult.DEL_SUCCESS;
    }

     /*
    * 更新BookBank
    * @param BookBanks : BookBank列表
    * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updBookBank(List<BookBank>bookBanks){
        try {
            for (BookBank bookBank : bookBanks){
                bookBankMapper.updateByPrimaryKeySelective(bookBank);
            }
        }catch (Exception e){
            return OpResult.UPD_ERROR;
        }
        return OpResult.UPD_SUCCESS;
    }

     /*
    * 获取Major
    * @return 返回操作错误信息以及Major列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getBookBank(){
        try {
            List<BookBank>bookBanks=bookBankMapper.getAll();
            Result<List<BookBank>> result = new Result<>();
            result.setError(new Error(0,"获取库存信息成功"));
            result.setData(bookBanks);
            return result;
        }catch (Exception e){
            return  OpResult.GET_ERROR;
        }
    }
}
