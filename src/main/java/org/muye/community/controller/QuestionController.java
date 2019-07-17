package org.muye.community.controller;

import org.muye.community.dto.QuestionDTO;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Zz
 * create 2019--07--17--00:43
 **/
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id")Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.queryQuestionById(id);
        model.addAttribute(questionDTO);
        return "question";
    }
}
