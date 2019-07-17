package org.muye.community.controller;

import org.muye.community.mapper.QuestionMapper;
import org.muye.community.mapper.UserMapper;
import org.muye.community.model.Question;
import org.muye.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Zz
 * create 2019--07--11--18:22
 **/
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {
        //简单处理判定内容逻辑 应放在前端
        if (question.getTitle().equals("") || question.getDescription().equals("") || question.getTag().equals("")) {
            model.addAttribute("error", "填写内容不可为空!");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
