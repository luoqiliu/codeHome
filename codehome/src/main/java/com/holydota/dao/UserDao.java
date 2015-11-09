package com.holydota.dao;

import java.util.List;

import com.holydota.bean.user.UserInfo;

public interface UserDao {
    public UserInfo getById(int id);
}
