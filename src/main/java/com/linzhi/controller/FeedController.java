package com.linzhi.controller;

import com.linzhi.model.EntityType;
import com.linzhi.model.Feed;
import com.linzhi.model.HostHolder;
import com.linzhi.service.FeedService;
import com.linzhi.service.FollowService;
import com.linzhi.util.JedisAdapter;
import com.linzhi.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林智 on 2018/2/26.
 */
@Controller
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    FeedService feedService;

    @Autowired
    FollowService followService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    JedisAdapter jedisAdapter;

    /**
     * 推模式
     * @param model
     * @return
     */
    @RequestMapping(path = {"/pushfeeds"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getPushFeeds(Model model) {
        int localUserId = hostHolder.getUser() != null ? hostHolder.getUser().getId() : 0;
        List<String> feedIds = jedisAdapter.lrange(RedisKeyUtil.getTimelineKey(localUserId), 0, 10);
        List<Feed> feeds = new ArrayList<Feed>();
        for (String feedId : feedIds) {
            Feed feed = feedService.getById(Integer.parseInt(feedId));
            if (feed != null) {
                feeds.add(feed);
            }
        }
        model.addAttribute("feeds", feeds);
        return "feeds";
    }

    /**
     * 拉模式
     * @param model
     * @return
     */
    @RequestMapping(path = {"/pullfeeds"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getPullFeeds(Model model) {
        //System.out.println(hostHolder.getUser() + " ----");
        int localUserId = hostHolder.getUser() != null ? hostHolder.getUser().getId() : 0;
        List<Integer> followees = new ArrayList<>();
        if (localUserId != 0) {
            // 关注的人
            followees = followService.getFollowees(localUserId, EntityType.ENTITY_USER, Integer.MAX_VALUE);
            System.out.println(followees);
        }
        List<Feed> feeds = null;
        // 没有粉丝的用户不该查询 feed流, 否则会将默认用户的 feed流 读取出来
        if (followees.size() != 0) {
            feeds = feedService.getUserFeeds(Integer.MAX_VALUE, followees, 10);
        }
        model.addAttribute("feeds", feeds);
        return "feeds";
    }
}