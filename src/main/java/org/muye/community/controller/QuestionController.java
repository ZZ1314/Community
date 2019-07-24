package org.muye.community.controller;

import org.muye.community.dto.CommentCreateDTO;
import org.muye.community.dto.CommentDTO;
import org.muye.community.dto.QuestionDTO;
import org.muye.community.mapper.OriQuestionMapper;
import org.muye.community.service.CommentService;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Zz
 * create 2019--07--17--00:43
 **/
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    OriQuestionMapper oriQuestionMapper;
    @Autowired
    CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model) {

        List<CommentDTO> comments= commentService.list(id);
        model.addAttribute(comments);

        oriQuestionMapper.increaseViewCount(id);
        QuestionDTO questionDTO = questionService.queryQuestionById(id);
        model.addAttribute(questionDTO);
        return "question";
    }



}
