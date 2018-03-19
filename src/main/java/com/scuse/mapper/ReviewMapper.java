package com.scuse.mapper;

import com.scuse.entity.Review;

public interface ReviewMapper {
    int insert(Review record);

    int insertSelective(Review record);
}