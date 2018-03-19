package com.scuse.mapper;

import com.scuse.entity.PaperTopic;

public interface PaperTopicMapper {
    int insert(PaperTopic record);

    int insertSelective(PaperTopic record);
}