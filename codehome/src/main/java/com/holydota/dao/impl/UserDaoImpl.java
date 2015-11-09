package com.holydota.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.holydota.bean.user.UserInfo;
import com.holydota.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
    @Resource(name = "codeHomeSqlSession")
    private SqlSession codeHomeSqlSession;

    @Override
    public UserInfo getById(int id) {
        return codeHomeSqlSession.selectOne("user.selectById", id);
    }

}
