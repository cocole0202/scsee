package com.scuse.mapper;

import com.scuse.entity.Plan;
import com.scuse.entity.Violation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);

    List<Plan> getAll();

    List<Plan> selectByBatchId(int batId);
}