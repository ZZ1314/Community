package org.muye.community.provider;

import org.muye.community.mapper.UserMapper;
import org.muye.community.model.User;
import org.muye.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zz
 * create 2019--07--17--11:21
 **/
@Component
public class UserProvider {
    @Autowired
    UserMapper userMapper;
    public User getUser(HttpServletRequest request) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    if (users.size() != 0) {
                        user=users.get(0);
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return user;
    }
}
