package com.holydota.service.impl;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.service.UserService;

public class UserServiceImpl implements UserService {

    private final static ILog log = LogFactory.getLog(UserServiceImpl.class);

    public String index() {
        log.error("dasdsasd");
        return "chedan";
    }

}
