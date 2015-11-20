package com.holydota.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.holydota.bean.user.UserInfo;
import com.holydota.common.interceptor.auth.AuthPassport;
import com.holydota.common.interceptor.count.CountTime;
import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.common.util.LocalSettingKeys;
import com.holydota.common.util.LocalSettings;
import com.holydota.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService                 userService;
    private ILog                logger = LogFactory.getLog(UserController.class);
    private static final String TEST   = LocalSettings.getProperty(LocalSettingKeys.CODEHOME_TEST_KEY);

    @CountTime(maxMilles = 3)
    @AuthPassport
    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserInfo chedan(HttpServletRequest request, HttpServletResponse response) {
        return userService.getUserByid(NumberUtils.toInt(request.getParameter("id")));
    }

    @ResponseBody
    @RequestMapping("name")
    public String getName(String name) {
        logger.info("name:" + name + TEST);
        return name + TEST;

    }

    @ResponseBody
    @RequestMapping("info")
    public String getInfo(UserInfo info) {
        logger.info("info.name:" + info.getName());
        return info.toString();

    }

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/account/login");
        return mv;
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public Map<String, String> index3() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "1");
        //map.put相当于request.setAttribute方法  
        return map;
    }
}
