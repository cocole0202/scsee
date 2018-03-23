package com.scuse.mapper;

import com.scuse.entity.Paper;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
}