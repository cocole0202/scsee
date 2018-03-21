package com.scuse.mapper;

import com.scuse.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {
    int insert(Score record);

    int insertSelective(Score record);

    List<Score> getAll();

    int updateByPrimaryKeySelective(Score record);

    int deleteByPrimaryKey(Integer id);
}