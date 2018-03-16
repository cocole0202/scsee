package com.scuse.service;

import com.scuse.entity.User;

import java.util.List;

/**
 * Created by Jaho on 2018/3/16.
 */
public interface UserService {

    int add(User user);

    User get(Integer id);
}
