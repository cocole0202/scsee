package com.scuse.mapper;

import com.scuse.entity.Register;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegisterMapper {
    int insert(Register record);

    int insertSelective(Register record);

    List<Register> selectByCandId(int id);
}