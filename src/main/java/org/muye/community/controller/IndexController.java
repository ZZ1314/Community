package org.muye.community.controller;

import org.muye.community.dto.PaginationDTO;
import org.muye.community.mapper.OriQuestionMapper;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.model.Question;
import org.muye.community.model.QuestionExample;
import org.muye.community.model.User;
import org.muye.community.provider.NotifyCountProvider;
import org.muye.community.provider.UserProvider;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private NotifyCountProvider notifyCountProvider;
    @Autowired
    OriQuestionMapper oriQuestionMapper;

    @GetMapping(value = {"/", "index"}, name = "主页跳转")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        HttpServletRequest request,
                        @RequestParam(name = "search", required = false) String search) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdIsNotNull();
        if (questionMapper.countByExample(questionExample) != 0) {
            if (search == null) {
                search = "";
            }else {
                search = search.replace('+', ' ');
                search = search.replace('?', ' ');
                search = search.replace('[', ' ');
                search = search.replace('\\', ' ');
                search = search.replace('|', ' ');
                search = search.replace('*', ' ');
                search = search.replace('(', ' ');
//                + ? [ \ | * (
                search = search.trim();
            }
            PaginationDTO paginationDTO = questionService.list(page, size, search);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("search", search);
        } else {
            //如果没有问题列表 数据库为空返回空对象
            model.addAttribute(new PaginationDTO());
        }
        User user = userProvider.getUser(request);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            notifyCountProvider.getNotifyCount(model, user);
        }
        return "index";
    }
}
