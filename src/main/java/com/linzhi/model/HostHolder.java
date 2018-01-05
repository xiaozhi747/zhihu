package com.linzhi.model;

import org.springframework.stereotype.Component;

/**
 * Created by 林智 on 2018/1/4.
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
