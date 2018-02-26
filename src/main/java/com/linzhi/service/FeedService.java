package com.linzhi.service;

import com.linzhi.model.Feed;

import java.util.List;

/**
 * Created by 林智 on 2018/2/26.
 */
public interface FeedService {

    List<Feed> getUserFeeds(int maxId, List<Integer> userIds, int count);

    boolean addFeed(Feed feed);

    Feed getById(int id);
}
