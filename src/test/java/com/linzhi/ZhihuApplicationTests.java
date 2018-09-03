package com.linzhi;

import com.linzhi.dao.MessageDAO;
import com.linzhi.model.Message;
import com.linzhi.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZhihuApplication.class)
public class ZhihuApplicationTests {

    @Autowired
    MessageDAO messageDAO;

    @Test
    public void testAddMessage() {
        for (int i = 0; i < 105; i++) {
            Message message = new Message();
            message.setContent("你好测试" + i);
            message.setCreatedDate(new Date());
            message.setFromId(16);
            message.setToId(15);
            message.setHasRead(0);
            messageDAO.addMessage(message);
        }
    }

}