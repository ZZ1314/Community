package org.muye.community.controller;

import org.muye.community.dto.PaginationDTO;
import org.muye.community.model.User;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zz
 * create 2019--07--16--00:34
 **/
@Controller
public class ProfileController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action")String action,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "size",defaultValue = "5")Integer size,
                          Model model, HttpServletRequest request){
        if (action.equals("question")){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }else if (action.equals("replies")){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        PaginationDTO paginationDTO = questionService.profileList(page,size,user);
        model.addAttribute(paginationDTO);

        return "profile";
    }
}
