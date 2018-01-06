package com.linzhi.service;

import com.linzhi.model.User;

import java.util.Map;

/**
 * Created by nowcoder on 2016/7/2.
 */
public interface UserService {

    User getUser(int id);

    Map<String,Object> register(String name, String password);

    Map<String, Object> login(String username, String password);

    void logout(String ticket);

    User selectByName(String name);
}
