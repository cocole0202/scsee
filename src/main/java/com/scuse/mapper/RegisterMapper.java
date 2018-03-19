package com.scuse.mapper;

import com.scuse.entity.Register;

public interface RegisterMapper {
    int insert(Register record);

    int insertSelective(Register record);
}