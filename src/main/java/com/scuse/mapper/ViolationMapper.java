package com.scuse.mapper;

import com.scuse.entity.Violation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationMapper {
    int insert(Violation record);

    int insertSelective(Violation record);

    List<Violation> getAll();

    int updateByPrimaryKeySelective(Violation record);

    int deleteByPrimaryKey(Integer id);
}