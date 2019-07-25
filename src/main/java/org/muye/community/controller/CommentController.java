package org.muye.community.controller;

import org.muye.community.dto.CommentCreateDTO;
import org.muye.community.dto.CommentResultDTO;
import org.muye.community.enums.NotificationEnum;
import org.muye.community.enums.NotificationStatusEnum;
import org.muye.community.mapper.CommentMapper;
import org.muye.community.mapper.NotificationMapper;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.model.Comment;
import org.muye.community.model.Notification;
import org.muye.community.model.Question;
import org.muye.community.model.User;
import org.muye.community.provider.NotifyCountProvider;
import org.muye.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zz
 * create 2019--07--22--21:55
 **/
@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    NotifyCountProvider notifyCountProvider;

    @ResponseBody
    @RequestMapping(value = "/comment")
    public Object postComment(@RequestBody(required = false) CommentCreateDTO commentCreateDTO,
                              HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommentResultDTO.errorOf(2002, "登录状态异常");
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentCreateDTO, comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        Boolean result = commentService.insert(comment);
        Map<Object, Object> objectMap = new HashMap<>();
        if (result) {
            objectMap.put("message", "请求成功");
            objectMap.put("code", "200");
            //添加通知
            createNotify(comment);
        }else {
            objectMap.put("message", "请求失败");
        }
        notifyCountProvider.getNotifyCount(model, user);
        return objectMap;
    }

    private void createNotify(Comment comment) {
        Question question = questionMapper.selectByPrimaryKey(comment.getParentId().intValue());
        Notification notification = new Notification();
        notification.setGmeCreate(System.currentTimeMillis());
        notification.setType(NotificationEnum.REPLY_COMMENT.getType());
        notification.setOuterid(comment.getParentId());
        notification.setNotifier(comment.getCommentator().longValue());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(question.getCreator().longValue());
        notificationMapper.insert(notification);
    }
}
