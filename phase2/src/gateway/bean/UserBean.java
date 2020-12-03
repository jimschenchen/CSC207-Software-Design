package gateway.bean;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-11-26 18:45
 **/
public class UserBean {

    private List<User> data = new ArrayList<>();

    public UserBean(User u) {
        this.data.add(u);
    }

    public Object convertToObject () {
        return data.get(0);
    }
}
