package com.linzhi.service;

import com.linzhi.model.Comment;

import java.util.List;

/**
 * Created by 林智 on 2018/1/6.
 */
public interface CommentService {
    List<Comment> getCommentsByEntity(int entityId, int entityType);

    int addComment(Comment comment);

    int getCommentCount(int entityId, int entityType) ;

    void deleteComment(int entityId, int entityType);

    Comment getCommentById(int id);

    int getUserCommentCount(int userId);
}
