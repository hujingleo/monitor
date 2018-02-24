package com.monitor.service;

import com.monitor.dao.UserDao;
import com.monitor.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/23.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User selectByUsername(@Param("username") String username) {
        return userDao.selectByUsername(username);
    }


    public int insertUser(@Param("username") String username, @Param("password") String password, @Param("user_type") int user_type) {
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setUser_type(user_type);
        entity.setCreated_date(new Date());
        return userDao.insertUser(entity);
    }
}
