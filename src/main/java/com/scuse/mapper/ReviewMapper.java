package com.scuse.mapper;

import com.scuse.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMapper {
    int insert(Review record);

    int insertSelective(Review record);

    Review selectType(Review review);

    int delete(Review review);
}