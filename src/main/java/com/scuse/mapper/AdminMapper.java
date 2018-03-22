package com.scuse.mapper;

import com.scuse.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByToken(String token);

    Admin selectByPhone(String phone);

    Admin selectByMail(String mail);

    Admin selectByIdNum(String idNum);

    Admin selectById(int id);

    int getMaxID();

    int updateByPrimaryKeySelective(Admin admin);

    List<Admin> getAll();
}