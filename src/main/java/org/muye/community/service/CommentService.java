package org.muye.community.service;

import org.muye.community.dto.CommentDTO;
import org.muye.community.enums.CommentTypeEnum;
import org.muye.community.mapper.CommentMapper;
import org.muye.community.mapper.OriQuestionMapper;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.mapper.UserMapper;
import org.muye.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zz
 * create 2019--07--23--12:09
 **/
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    OriQuestionMapper oriQuestionMapper;
    @Autowired
    UserMapper userMapper;
    @Transactional
    public boolean insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0 || comment.getContent().equals("")) {
            return false;
        }
        commentMapper.insert(comment);
        oriQuestionMapper.increaseCommentCount(comment.getParentId().intValue());
        return true;
    }

    public List<CommentDTO> list(Integer id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id.longValue()).andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        commentExample.setOrderByClause("gmt_create");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if(comments.size()==0){
            return new ArrayList<>();
        }

        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
