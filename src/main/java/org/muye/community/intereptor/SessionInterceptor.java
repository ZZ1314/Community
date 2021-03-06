package org.muye.community.intereptor;

import org.muye.community.model.User;
import org.muye.community.provider.NotifyCountProvider;
import org.muye.community.provider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zz
 * create 2019--07--16--13:55
 **/
@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    NotifyCountProvider notifyCountProvider;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userProvider.getUser(request);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return true;
        } else {
            request.setAttribute("msg", "请登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
    }
}
