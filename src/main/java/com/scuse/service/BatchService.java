package com.scuse.service;

import java.util.List;
import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Batch;
import com.scuse.mapper.BatchMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jaho on 2018/3/20.
 *
 * Batch 相关跨表操作
 */

@Repository
public class BatchService {

     @Autowired
     BatchMapper batchMapper;

    /*
     * 添加Batch
     * @param batches: batch列表
     * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result addBatch(List<Batch> batches) {
        try{
            for (Batch batch:batches) {
                batchMapper.insert(batch);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
     * 删除Batch
     * @param ids: 需要删除的batch的id列表
     * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delBatch(List<Integer> ids) {
        try{
            for (Integer id:ids) {
                batchMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Batch
     * @param batches: batch列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updBatch(List<Batch> batches) {
        try{
            for (Batch batch:batches) {
                batchMapper.updateByPrimaryKeySelective(batch);
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取Batch
     * @return 返回操作错误信息以及Batch列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getBatches() {
        try{
            List<Batch> batches = batchMapper.getAll();
            Result<List<Batch>> result = new Result<>();
            result.setError(new Error(0, "获取考次信息成功"));
            result.setData(batches);
            return result;
        }catch (Exception e) {
            return OpResult.GET_ERROR;
        }
    }
}
