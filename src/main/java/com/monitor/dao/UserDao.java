package com.monitor.dao;

import com.monitor.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/2/23.
 */
public interface UserDao {
    User selectByUsername(@Param("username") String username);

    int insertUser(User entity);
}
