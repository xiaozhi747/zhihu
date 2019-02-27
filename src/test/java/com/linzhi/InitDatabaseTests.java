package com.linzhi;

import com.linzhi.dao.QuestionDAO;
import com.linzhi.dao.UserDAO;
import com.linzhi.model.EntityType;
import com.linzhi.model.Question;
import com.linzhi.model.User;
import com.linzhi.service.FollowService;
import com.linzhi.service.SensitiveService;
import com.linzhi.util.JedisAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZhihuApplication.class)
//@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    SensitiveService sensitiveUtil;

    @Autowired
    FollowService followService;

    @Autowired
    JedisAdapter jedisAdapter;

    @Test
    public void contextLoads() {
        Random random = new Random();
        jedisAdapter.getJedis().flushDB();
        for (int i = 0; i < 11; ++i) {
            User user = new User();
            user.setHeadUrl(String.format("/images/img/avatars/%d.jpg", random.nextInt(1000) + 1000));
            user.setName(String.format("测试用户%d", i+11));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);

            for (int j = 1; j < i; ++j) {
                followService.follow(j, EntityType.ENTITY_USER, i);
            }

            user.setPassword("newpassword");
            userDAO.updatePassword(user);

            Question question = new Question();
            question.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            question.setCreatedDate(date);
            question.setUserId(i + 1);
            question.setTitle(String.format("问题{%d}", i));
            question.setContent(String.format("测试评论 %d", i));
            questionDAO.addQuestion(question);
        }

        Assert.assertEquals("newpassword", userDAO.selectById(1).getPassword());
        //userDAO.deleteById(1);
        //Assert.assertNull(userDAO.selectById(1));
    }

    /*
    @Test
    public void testSensitive() {
        String content = "question content <img src=\"https:\\/\\/baidu.com/ff.png\">色情赌博";
        String result = sensitiveUtil.filter(content);
        System.out.println(result);
    }*/
}
