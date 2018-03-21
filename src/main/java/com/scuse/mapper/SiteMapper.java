package com.scuse.mapper;

import com.scuse.entity.Site;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    List<Site> getAll();
}