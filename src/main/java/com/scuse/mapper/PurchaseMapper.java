package com.scuse.mapper;

import com.scuse.entity.Purchase;

public interface PurchaseMapper {
    int insert(Purchase record);

    int insertSelective(Purchase record);
}