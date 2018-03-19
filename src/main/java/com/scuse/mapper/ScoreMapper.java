package com.scuse.mapper;

import com.scuse.entity.Score;

public interface ScoreMapper {
    int insert(Score record);

    int insertSelective(Score record);
}