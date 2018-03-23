package com.scuse.mapper;

import com.scuse.entity.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseMapper {
    int insert(Purchase record);

    int insertSelective(Purchase record);
}