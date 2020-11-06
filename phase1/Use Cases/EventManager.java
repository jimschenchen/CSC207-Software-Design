import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private Event event;

    public List<Integer> add_user_ids(List<User> user){
        List<Integer> all_ids = new ArrayList<>();
        for (int z = 0; z < event.getUsers_lst().size(); z ++){
            all_ids.add(event.getUsers_lst().get(z).getUser_id());
        }
        return all_ids;
    }

    public boolean can_remove(List<User> user){
        List<Integer> all_ids = add_user_ids(user);
        int a = 0;
        for (int i = 0; i < user.size(); i++) {
            if (all_ids.contains(user.get(i).getUser_id())) {
                a++;
            }
        }
        if (user.size() == a){
            return true;
        }
        return false;
    }

    public boolean remove_user(List<User> user) {
        if (can_remove(user) == false){
            return false;
        }
        for (int i = 0; i < user.size(); i++) {
            event.remove(user.get(i));
            }
        return true;
    }

    public void add_multi_users(List<User> user){
        List<Integer> all_ids = add_user_ids(user);
        for (int i = 0; i < user.size(); i ++){
            boolean flag = false;
            for (int j = 0; j  < event.getUsers_lst().size(); j ++){
                if (all_ids.contains(user.get(i).getUser_id())){
                    flag = true;
                }
            }
            if (flag == false){
                event.add(user.get(i));
            }
        }
    }
}



//    read events from database and create map of events with key (room) and value (event) sorted by time.
//        create a new event if it is not conflict with current events.
//        save new event to database and add it to list.
//        set the Speaker for event
//        add new signed up user to the event.
//        delete current signed up user from event.
//        getter for start time, room, speaker, user list
//        getter for the event list