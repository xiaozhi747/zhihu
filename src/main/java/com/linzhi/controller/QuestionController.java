package com.linzhi.controller;

import com.linzhi.model.HostHolder;
import com.linzhi.model.Question;
import com.linzhi.service.QuestionService;
import com.linzhi.service.UserService;
import com.linzhi.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static com.linzhi.configuration.Constants.NOT_LOGGED_IN_STATUS_CODE;

/**
 * Created by 林智 on 2018/1/5.
 */
@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    QuestionService questionService;

   /* @Autowired
    CommentService commentService;*/

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

  /*  @RequestMapping(value = "/question/{qid}", method = {RequestMethod.GET})
    public String questionDetail(Model model, @PathVariable("qid") int qid) {
        Question question = questionService.getById(qid);
        model.addAttribute("question", question);
        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> vos = new ArrayList<>();
        for (Comment comment : commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment", comment);
            vo.set("user", userService.getUser(comment.getUserId()));
            vos.add(vo);
        }
        model.addAttribute("comments", vos);

        return "detail";
    }*/

    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
        try {
            Question question = new Question();
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);
            if (hostHolder.getUser() == null) {             //todo
                //question.setUserId(ANONYMOUS_USERID);     //未登录则返回匿名用户
                 return WendaUtil.getJSONString(NOT_LOGGED_IN_STATUS_CODE);    //未登录则返回 999 状态码, 让用户跳转到登录页面去登录
            } else {
                question.setUserId(hostHolder.getUser().getId());
            }
            if (questionService.addQuestion(question) > 0) {
                return WendaUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加题目失败" + e.getMessage());
        }
        return WendaUtil.getJSONString(1, "失败");
    }

}
