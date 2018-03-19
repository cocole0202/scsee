package com.scuse.mapper;

import com.scuse.entity.Admin;

public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);
}