package com.scuse.mapper;

import com.scuse.entity.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    List<Major> getAll();
}