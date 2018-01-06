package com.linzhi.service;

import com.linzhi.model.Message;

import java.util.List;

/**
 * Created by 林智 on 2018/1/6.
 */
public interface MessageService {
    int addMessage(Message message);

    List<Message> getConversationDetail(String conversationId, int offset, int limit);

    List<Message> getConversationList(int userId, int offset, int limit);

    int getConvesationUnreadCount(int userId, String conversationId);
}
