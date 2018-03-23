package com.scuse.mapper;

import com.scuse.entity.Review;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewMapper {
    int insert(Review record);

    int insertSelective(Review record);

    Review selectType(Review review);

    int delete(Review review);

    List<Review> selectById(int id);

    List<Review> selectByType(int type);

    List<Review> selectByTypeWithTwoTypes(int type1,int type2);

    List<Review> getAll();
}