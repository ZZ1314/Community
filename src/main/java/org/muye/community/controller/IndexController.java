package org.muye.community.controller;

import org.muye.community.dto.PaginationDTO;
import org.muye.community.dto.QuestionDTO;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.mapper.UserMapper;
import org.muye.community.model.User;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zz
 * create 2019--07--10--12:18
 **/
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping(value = {"/", "index"}, name = "主页跳转")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        //寻找访问主页时request的cookie否含有token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //根据token寻找数据库中用户信息
                    User user = userMapper.findByToken(token);
                    //如果有该用户信息 将用户信息写入session
                    if (user != null) {
                        request.getSession().setAttribute("githubUser", user);
                    }
                    break;
                }
            }
        }
        //刷新列表信息
        if (questionMapper.count() != 0) {
            PaginationDTO paginationDTO = questionService.list(page, size);
            model.addAttribute(paginationDTO);
        }else {
            PaginationDTO paginationDTO=new PaginationDTO();
            model.addAttribute(paginationDTO);
        }
        return "index";
    }
}
