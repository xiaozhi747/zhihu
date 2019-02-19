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
    private User userHelp;
    // 临时方案, 为了配合首页的分页才采用的
    private Question questionHelp;
    // 临时方案, 为了配合首页的分页才采用的
    private long followCountHelp;

    public void setUserHelp(User userHelp) {
        this.userHelp = userHelp;
    }

    public void setQuestionHelp(Question questionHelp) {
        this.questionHelp = questionHelp;
    }

    public void setFollowCountHelp(long followCountHelp) {
        this.followCountHelp = followCountHelp;
    }

    public User getUserHelp() {

        return userHelp;
    }

    public Question getQuestionHelp() {
        return questionHelp;
    }

    public long getFollowCountHelp() {
        return followCountHelp;
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

