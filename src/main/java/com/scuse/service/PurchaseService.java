package com.scuse.service;


import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Purchase;
import com.scuse.mapper.PurchaseMapper;
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
public class PurchaseService {
    @Autowired
    PurchaseMapper purchaseMapper;

    /*
    * 添加Purchase
    * @param Purchases: Purchase列表
    * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result addPurchase(List<Purchase> purchases){
        try {
            for (Purchase purchase:purchases){
                purchaseMapper.insertSelective(purchase);
            }
        }catch (Exception e){
            return OpResult.ADD_ERROR;
        }
        return  OpResult.ADD_SUCCESS;
    }

     /*
    * 删除Purchase
    * @param Purchase : 需要删除的Purchase的id列表
    * @return 返回删除操作错误信息
     */

}
