package gateway.bean;

import entity.User;
import entity.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-12-04 00:31
 **/
public class EventBean {
    private List<Event> data = new ArrayList<>();

    public EventBean(Event e) {
        this.data.add(e);
    }

    public Object convertToObject () {
        return data.get(0);
    }
}
