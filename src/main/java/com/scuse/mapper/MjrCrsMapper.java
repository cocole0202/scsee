package com.scuse.mapper;

import com.scuse.entity.MjrCrs;
import org.springframework.stereotype.Repository;

@Repository
public interface MjrCrsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MjrCrs record);

    int insertSelective(MjrCrs record);

    MjrCrs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MjrCrs record);

    int updateByPrimaryKey(MjrCrs record);
}