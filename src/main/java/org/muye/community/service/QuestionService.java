package org.muye.community.service;

import org.muye.community.dto.QuestionDTO;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.mapper.UserMapper;
import org.muye.community.model.Question;
import org.muye.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zz
 * create 2019--07--12--22:55
 **/
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        List<Question> questions = questionMapper.list();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
