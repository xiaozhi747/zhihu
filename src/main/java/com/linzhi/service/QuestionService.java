package com.linzhi.service;

import com.linzhi.model.Question;

import java.util.List;

/**
 * Created by 林智 on 2018/1/4.
 */
public interface QuestionService {
    Question getById(int id);

    int addQuestion(Question question);

    List<Question> getLatestQuestions(int userId, int offset, int limit) ;

    int updateCommentCount(int id, int count);
}
