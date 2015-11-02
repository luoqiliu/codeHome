package com.holydota.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.holydota.common.network.BasePostMethod;

@Controller
@RequestMapping("/tools")
public class UrlController {
    @ResponseBody
    @RequestMapping(value = "/shorturl", method = RequestMethod.GET)
    public String getShortUrl(HttpServletRequest req, HttpServletResponse resp) {
        if (req == null) {
            return "invaild param";
        }
        String url = req.getParameter("u");
        if (StringUtils.isBlank(url)) {
            return "invaild param";
        }
        final String rurl = "http://dwz.cn/create.php";
        final String param = "url=" + url;
        String haha = BasePostMethod.sendPost(rurl, param);
        return haha;
    }
}
