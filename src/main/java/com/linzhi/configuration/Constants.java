package com.linzhi.configuration;

/**
 * 常量类
 * Created by 林智 on 2018/1/5.
 */
public class Constants {
    public static final int TOKEN_TIME = 3600 * 24 * 100;   //token 过期时间 100 天
    public static final int COOKIE_TIME = 3600 * 24 * 5;    //cookie 过期时间 5 天
    public static int ANONYMOUS_USERID = 3;              //匿名用户 id
    public static int NOT_LOGGED_IN_STATUS_CODE = 999;  //未登录时返回的状态码
}
