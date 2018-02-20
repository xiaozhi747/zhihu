package com.linzhi.async.handle;

import com.linzhi.async.EventHandler;
import com.linzhi.model.EventModel;
import com.linzhi.model.EventType;
import com.linzhi.model.Message;
import com.linzhi.model.User;
import com.linzhi.service.MessageService;
import com.linzhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.linzhi.configuration.Constants.SYSTEM_USERID;

/**
 * Created by 林智 on 2018/2/14.
 */
@Component
public class LikeHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());
        message.setContent("用户" + user.getName()
                + "赞了你的评论,http://127.0.0.1:8080/question/" + model.getExt("questionId"));

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}