package com.scuse.mapper;

import com.scuse.entity.PlanSite;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanSiteMapper {
    int insert(PlanSite record);

    int insertSelective(PlanSite record);

    List<PlanSite> selectByPlanId(int id);
}