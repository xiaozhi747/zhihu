package com.linzhi.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by 林智 on 2018/2/26.
 *
 * feed 流 class
 */
public class Feed {
    private int id;
    private int type;           //feed 的类型
    private int userId;         //触发者的 id
    private Date createdDate;
    private String data;        //data 为 json 格式.
    private JSONObject dataJSON = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getData() {
        return data;
    }

    // data 赋值的同时将 dataJSON 也赋值, 方便下面的 get() 取值
    public void setData(String data) {
        this.data = data;
        dataJSON = JSONObject.parseObject(data);
    }

    /**
     * velocity 中 $obj.xxx 对应 model 中的以下几种方法  如 obj.getxxx(), obj.get("xxx"), obj.isxxx()
     * 由于 data 中的属性是不确定的, 所以使用 get("xxx") 的方式来获取 value 是合理的
     */
    public String get(String key) {
        return dataJSON == null ? null : dataJSON.getString(key);
    }

}
