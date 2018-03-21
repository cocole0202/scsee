package com.scuse.mapper;

import com.scuse.entity.MjrCrs;

public interface MjrCrsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MjrCrs record);

    int insertSelective(MjrCrs record);

    MjrCrs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MjrCrs record);

    int updateByPrimaryKey(MjrCrs record);
}