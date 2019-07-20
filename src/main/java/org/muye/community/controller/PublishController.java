package org.muye.community.controller;

import org.muye.community.mapper.QuestionMapper;
import org.muye.community.model.Question;
import org.muye.community.model.QuestionExample;
import org.muye.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zz
 * create 2019--07--11--18:22
 **/
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

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
        if (question.getId() == null) {
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setViewCount(0);
            questionMapper.insert(question);
            return "redirect:/";
        }else {
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question, questionExample);
            return "redirect:/";
        }
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,Model model) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(questionExample);
//        List<Question> questions = questionMapper.selectByExample(questionExample);
        Question question = questions.get(0);
        model.addAttribute(question);
        return "publish";
    }
}
