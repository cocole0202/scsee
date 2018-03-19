package com.scuse.mapper;

import com.scuse.entity.Violation;

public interface ViolationMapper {
    int insert(Violation record);

    int insertSelective(Violation record);
}