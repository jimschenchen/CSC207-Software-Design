import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class EventManager {
    private Event event;
    //private Map<Room, Event> all_events;
    //不知道怎么读取database， 所以我用all_events来代表map of events with key (room) and value (event) sorted by time.
    //Map<Room, Event> all_events = new HashMap<Room, Event>();
    //我觉得map的key直接写时间会不会好一些，因为这样会更加容易sort
    //疑问：我们是不是应该创建一个constructor来写all_events?
    //public EventManager(){
    //  this.all_events = ...   #。。。表示database中的相关信息
    //}
    //我下面所有代码都是根据假设需要创造这个constructor来写的

    // 希望可以分開canAddNewEvent和addNewEvent兩個method -grace
    public void add_new_event(Event event){

        int cond = 0;
        for(Room r : all_events){
            if (r.getRoom_num() == event.getRoom_num()){
                cond = 1;
            }
            if (all_events[r].getStart_time() == event.getStart_time()){
                cond = 1;
            }
        }
        if(cond == 0){
            all_events.put(event.getRoomId(), event);  //这样是不会按时间顺序添加event到all_events里面的
            //这一行是留给“添加new event to database的，不写是因为不会database
        }
    }

    // 希望可以分開爲兩個method，canSetSpeaker & setSpeaker
    public void setSpeaker(Speaker speaker, Event event){
        int cond = 0;
        for(Event eve: speaker.get_eventlist()){
            if (eve.getStart_time() == event.getStart_time()){
                cond = 1;
            }
        }
        if (cond == 0){
            event.setSpeaker(speaker);
        }
    }

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

    // add user to event
    public void addUserToEvent(User user, Event event){}
}



//    read events from database and create map of events with key (room) and value (event) sorted by time.
//        create a new event if it is not conflict with current events.
//        save new event to database and add it to list.
//        set the Speaker for event
//        add new signed up user to the event.
//        delete current signed up user from event.
//        getter for start time, room, speaker, user list
//        getter for the event list