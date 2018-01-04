package com.linzhi.service.impl;

import com.linzhi.dao.QuestionDAO;
import com.linzhi.model.Question;
import com.linzhi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by 林智 on 2018/1/4.
 */
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionDAO questionDAO;

    @Override
    public Question getById(int id) {
        return questionDAO.getById(id);
    }

    @Override
    public int addQuestion(Question question) {
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        // 敏感词过滤
       // question.setTitle(sensitiveService.filter(question.getTitle()));
       // question.setContent(sensitiveService.filter(question.getContent()));
        return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
    }

    @Override
    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset, limit);
    }

    /**
     * 修改问题评论总数
     * @param id
     * @param count
     * @return
     */
    @Override
    public int updateCommentCount(int id, int count) {
        return questionDAO.updateCommentCount(id, count);
    }
}
