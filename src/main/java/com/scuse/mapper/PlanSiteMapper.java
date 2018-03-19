package com.scuse.mapper;

import com.scuse.entity.PlanSite;

public interface PlanSiteMapper {
    int insert(PlanSite record);

    int insertSelective(PlanSite record);
}