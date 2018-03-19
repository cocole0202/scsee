package com.scuse.mapper;

import com.scuse.entity.Batch;

public interface BatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Batch record);

    int insertSelective(Batch record);

    Batch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Batch record);

    int updateByPrimaryKey(Batch record);
}