package com.scuse.service;

import com.scuse.entity.User;
import com.scuse.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jaho on 2018/3/16.
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int add(User user) {
        return 0;
    }

    @Override
    public User get(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
