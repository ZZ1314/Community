package org.muye.community.controller;

import org.muye.community.dto.PaginationDTO;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.model.User;
import org.muye.community.provider.UserProvider;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zz
 * create 2019--07--10--12:18
 **/
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserProvider userProvider;

    @GetMapping(value = {"/", "index"}, name = "主页跳转")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        HttpServletRequest request) {
        //刷新列表信息
        if (questionMapper.count() != 0) {
            PaginationDTO paginationDTO = questionService.list(page, size);
            model.addAttribute(paginationDTO);
        }else {
            //如果没有问题列表 数据库为空返回空对象
            model.addAttribute(new PaginationDTO());
        }
        User user = userProvider.getUser(request);
        if(user!=null){
            request.getSession().setAttribute("user", user);
        }
        return "index";
    }
}
