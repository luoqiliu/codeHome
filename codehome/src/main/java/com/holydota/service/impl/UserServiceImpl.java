package com.holydota.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holydota.bean.user.BaseFreezeRule;
import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.dao.impl.FreezeRuleDaoImpl;
import com.holydota.service.UserService;

@Service("userService")
class UserServiceImpl implements UserService {

    private final static ILog log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private FreezeRuleDaoImpl freezeRuleDaoImpl;

    public String index() {
        BaseFreezeRule rule = new BaseFreezeRule();
        rule.setCityId(330000);
        List<BaseFreezeRule> allRules;
        allRules = freezeRuleDaoImpl.selectAll(false);

        log.error("dasdsasd");
        return "chedan";
    }

}
