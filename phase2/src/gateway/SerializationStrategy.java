package gateway;

import com.google.gson.Gson;
import entity.User;
import entity.event.Event;
import gateway.bean.EventBean;
import gateway.bean.UserBean;

import java.lang.reflect.Type;

/**
 * @program: group_0173
 * @description: Serialization Strategy to determine which data bean should be used to ser/deser specific type of data
 * @create: 2020-12-03 23:38
 **/
public class SerializationStrategy {

    public Object deserialize (String data, Gson gson, Type type) {
        if (type.equals(User.class)) {  // User Class
            UserBean userBean = gson.fromJson(data, UserBean.class);
            return userBean.convertToObject();
        } else if (type.equals(Event.class)) {  // Event Class
            EventBean eventBean = gson.fromJson(data, EventBean.class);
            return eventBean.convertToObject();
        }
        return null;
    }

    public String serialize (Object obj, Gson gson, Type type) {
        if (type.equals(User.class)) {  // User Class
            return gson.toJson(new UserBean((User)obj));
        } else if (type.equals(Event.class)) {  // Event Class
            return gson.toJson(new EventBean((Event)obj));
        }
        return null;
    }
}
