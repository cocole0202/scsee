package com.scuse.mapper;

import com.scuse.entity.Batch;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Batch record);

    int insertSelective(Batch record);

    Batch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Batch record);

    int updateByPrimaryKey(Batch record);

    List<Batch> getAll();
}