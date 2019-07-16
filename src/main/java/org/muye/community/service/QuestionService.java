package org.muye.community.service;

import org.muye.community.dto.PaginationDTO;
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
    public PaginationDTO list(Integer page, Integer size) {
        //查询总数据计算总页数
        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if (totalCount % size ==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }
        //对page范围进行约束
        page=page<1?1:page;
        page=page>totalPage?totalPage:page;
        //计算分页的offset
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //遍历question列表获取用户信息 加入questionDTO中传输
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        //通过DTO内部方法赋值
        paginationDTO.setPagination(totalCount,page,size,totalPage);
        return paginationDTO;
    }
    public PaginationDTO profileList(Integer page, Integer size,User user) {
        //查询总数据计算总页数
        Integer totalPage;
        Integer totalCount = questionMapper.queryCountByCreator(user.getId());
        if (totalCount % size ==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }
        //对page范围进行约束
        page=page<1?1:page;
        page=page>totalPage?totalPage:page;
        //计算分页的offset
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.queryQuestionByCreator(user.getId(),offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //遍历question列表获取用户信息 加入questionDTO中传输
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        //通过DTO内部方法赋值
        paginationDTO.setPagination(totalCount,page,size,totalPage);
        return paginationDTO;
    }


}
