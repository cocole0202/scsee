package com.scuse.mapper;

import com.scuse.entity.PaperTopic;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperTopicMapper {
    int insert(PaperTopic record);

    int insertSelective(PaperTopic record);
}