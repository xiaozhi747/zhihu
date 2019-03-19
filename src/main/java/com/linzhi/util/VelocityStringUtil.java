package com.linzhi.util;

/**
 * Created by 林智 on 2019/3/18.
 */
public class VelocityStringUtil {
    public String subStr(String str, int end) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (str.length() > end) {
            return str.substring(0, end) + "...";
        }
        return str;
    }
}
