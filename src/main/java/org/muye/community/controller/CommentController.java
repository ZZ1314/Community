package org.muye.community.controller;

import org.muye.community.dto.CommentCreateDTO;
import org.muye.community.dto.CommentResultDTO;
import org.muye.community.mapper.CommentMapper;
import org.muye.community.model.Comment;
import org.muye.community.model.User;
import org.muye.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @RequestMapping(value = "/comment")
    public Object postComment(@RequestBody(required = false) CommentCreateDTO commentCreateDTO,
                              HttpServletRequest request) {
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
        }else {
            objectMap.put("message", "请求失败");
        }
        return objectMap;
    }
}
