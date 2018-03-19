package com.scuse.mapper;

import com.scuse.entity.TopicBank;

public interface TopicBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicBank record);

    int insertSelective(TopicBank record);

    TopicBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicBank record);

    int updateByPrimaryKey(TopicBank record);
}