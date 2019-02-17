package com.linzhi.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林智 on 2018/1/4.
 */
public class ViewObject implements Serializable {
    private Map<String, Object> objs = new HashMap<String, Object>();
    // 临时方案, 为了配合首页的分页才采用的
    private User user;
    // 临时方案, 为了配合首页的分页才采用的
    private Question question;
    // 临时方案, 为了配合首页的分页才采用的
    private long followCount;

    public void setFollowCount(long followCount) {
        this.followCount = followCount;
    }

    public long getFollowCount() {

        return followCount;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {

        return question;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }

    @Override
    public String toString() {
        return "ViewObject{" +
                "objs=" + objs +
                '}';
    }

    public void setObjs(Map<String, Object> objs) {
        this.objs = objs;
    }

    public Map<String, Object> getObjs() {

        return objs;
    }
}

