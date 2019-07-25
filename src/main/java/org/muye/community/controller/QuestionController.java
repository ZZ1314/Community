package org.muye.community.controller;

import org.muye.community.dto.CommentDTO;
import org.muye.community.dto.QuestionDTO;
import org.muye.community.mapper.NotificationMapper;
import org.muye.community.mapper.OriQuestionMapper;
import org.muye.community.model.Notification;
import org.muye.community.model.NotificationExample;
import org.muye.community.model.User;
import org.muye.community.provider.NotifyCountProvider;
import org.muye.community.service.CommentService;
import org.muye.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
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
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    NotifyCountProvider notifyCountProvider;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Integer notifyId,
                           Model model,
                           HttpSession session) {
        //获得回复列表
        List<CommentDTO> commentDTOList= commentService.list(id);
        model.addAttribute("commentDTOList",commentDTOList);
        //获得问题列表
        oriQuestionMapper.increaseViewCount(id);
        QuestionDTO questionDTO = questionService.queryQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        //获得相关问题
        List<QuestionDTO> relatedQuestion = questionService.selectRelated(questionDTO);
        model.addAttribute("relatedQuestion",relatedQuestion);
        //如果有回复更改已读状态
        if(notifyId!=null){
            Notification notification = new Notification();
            notification.setStatus(1);
            NotificationExample notificationExample = new NotificationExample();
            notificationExample.createCriteria().andIdEqualTo(notifyId.longValue());
            notificationMapper.updateByExampleSelective(notification,notificationExample);
        }
        User user = (User) session.getAttribute("user");
        notifyCountProvider.getNotifyCount(model, user);
        return "question";
    }



}
