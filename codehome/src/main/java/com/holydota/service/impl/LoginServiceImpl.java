package com.holydota.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.holydota.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean checkLogin(String idx, String token) {
        Map<String, String> idxs = new HashMap<String, String>();
        idxs.put("luoqi", "haha");
        if (!idxs.containsKey(idx)) {
            return false;
        } else {
            if (StringUtils.equals(token, idxs.get(idx))) {
                return true;
            } else {
                return false;
            }
        }
    }

}
