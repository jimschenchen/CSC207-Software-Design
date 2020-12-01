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
public class UserBean {

    private List<User> user = new ArrayList<>();

    public UserBean(User u) {
        this.user.add(u);
    }

    public User convertToUser () {
        return user.get(0);
    }
}
