package gateway;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-11-26 18:45
 **/
public class DataBean {

    private List data = new ArrayList<>();

    public DataBean(Object o) {
        this.data.add(o);
    }

    public Object convertToObject () {
        return data.get(0);
    }
}
