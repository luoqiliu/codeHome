package com.holydota.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.holydota.bean.user.BaseFreezeRule;

@Repository
public class FreezeRuleDaoImpl {

    private static final String DRIVER_TABLE = "DriverFreezeRule";
    private static final String PASS_TABLE   = "PassengerFreezeRule";

    @Resource(name = "codeHomeSqlSession")
    private SqlSession          userSqlSession;

    public List<BaseFreezeRule> selectAll(boolean isDriver) {
        return userSqlSession.selectList((isDriver ? DRIVER_TABLE : PASS_TABLE) + ".selectAll");
    }

}
