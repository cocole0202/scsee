package com.scuse.mapper;

import com.scuse.entity.TopicType;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicType record);

    int insertSelective(TopicType record);

    TopicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicType record);

    int updateByPrimaryKey(TopicType record);
}