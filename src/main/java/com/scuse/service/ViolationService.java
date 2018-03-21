package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Violation;
import com.scuse.mapper.ViolationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ViolationService {

    @Autowired
    ViolationMapper violationMapper;

    /*
     * 添加Violation
     * @param violation: violation列表
     * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public
    Result addViolation(List<Violation> violations) {
        try{
            for (Violation violation:violations) {
                violationMapper.insert(violation);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
     * 删除Violation
     * @param ids: 需要删除的Violation的id列表
     * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delViolation(int[] cand_ids) {
        try{
            for (Integer cand_id:cand_ids) {
                violationMapper.deleteByPrimaryKey(cand_id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Violation
     * @param violation: violation列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updViolation(List<Violation> violations) {
        try{
            for (Violation violation:violations) {
                violationMapper.updateByPrimaryKeySelective(violation);
            }
        }catch (Exception e) {
            System.out.println(e);
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取violation
     * @return 返回操作错误信息以及violation列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getViolation() {
        try{
            List<Violation> violations = violationMapper.getAll();
            Result<List<Violation>> result = new Result<>();
            result.setError(new Error(0, "获取违规信息成功"));
            result.setData(violations);
            return result;
        }catch (Exception e) {
            return OpResult.GET_ERROR;
        }
    }
}
