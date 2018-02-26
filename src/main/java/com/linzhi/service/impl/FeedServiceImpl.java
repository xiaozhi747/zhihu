package com.linzhi.service.impl;

import com.linzhi.dao.FeedDAO;
import com.linzhi.model.Feed;
import com.linzhi.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 林智 on 2018/2/26.
 */
@Service
public class FeedServiceImpl implements FeedService{

    @Autowired
    FeedDAO feedDAO;

    @Autowired
    public List<Feed> getUserFeeds(int maxId, List<Integer> userIds, int count) {
        return feedDAO.selectUserFeeds(maxId, userIds, count);
    }

    @Autowired
    public boolean addFeed(Feed feed) {
        feedDAO.addFeed(feed);
        return feed.getId() > 0;
    }

    @Autowired
    public Feed getById(int id) {
        return feedDAO.getFeedById(id);
    }
}
