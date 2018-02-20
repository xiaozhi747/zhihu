package com.linzhi.async;

import com.linzhi.model.EventModel;
import com.linzhi.model.EventType;

import java.util.List;

/**
 * Created by 林智 on 2018/2/14.
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}