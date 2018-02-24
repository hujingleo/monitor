package com.monitor.dao;


import com.monitor.model.UserInfo;

public interface UserInfoDao{
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}