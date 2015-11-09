package com.holydota.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holydota.bean.user.UserInfo;
import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.dao.impl.UserDaoImpl;
import com.holydota.service.UserService;

@Service("userService")
class UserServiceImpl implements UserService {

    private final static ILog log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private UserDaoImpl       userDao;

    @Override
    public UserInfo getUserByid(int id) {
        return userDao.getById(id);
    }

}
