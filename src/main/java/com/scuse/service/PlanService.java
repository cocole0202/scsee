package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Plan;
import com.scuse.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PlanService {

    @Autowired
    PlanMapper planMapper;

    /*
     * 添加Plan
     * @param plan: plan列表
     * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public
    Result addPlan(List<Plan> plans) {
        try{
            for (Plan plan:plans) {
                planMapper.insert(plan);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
     * 删除Plan
     * @param ids: 需要删除的plan的id列表
     * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delPlan(int[] ids) {
        try{
            for (Integer id:ids) {
                planMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Plan
     * @param room: plan列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updPlan(List<Plan> plans) {
        try{
            for (Plan plan:plans) {
                planMapper.updateByPrimaryKeySelective(plan);
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取plan
     * @return 返回操作错误信息以及Plan列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getPlan() {
        try{
            List<Plan> plans = planMapper.getAll();
            Result<List<Plan>> result = new Result<>();
            result.setError(new Error(0, "获取作弊信息成功"));
            result.setData(plans);
            return result;
        }catch (Exception e) {
            return OpResult.GET_ERROR;
        }
    }
}
