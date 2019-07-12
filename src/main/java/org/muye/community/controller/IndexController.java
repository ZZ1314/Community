package org.muye.community.controller;

import org.muye.community.mapper.UserMapper;
import org.muye.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Zz
 * create 2019--07--10--12:18
 **/
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = {"/", "index"}, name = "主页跳转")
    public String index(HttpServletRequest request) {
        //寻找访问主页时request的cookie否含有token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //根据token寻找数据库中用户信息
                    User user = userMapper.findByToken(token);
                    System.out.println(user);
                    //如果有该用户信息 将用户信息写入session
                    if (user != null) {
                        request.getSession().setAttribute("githubUser", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
