import java.util.ArrayList;
import java.util.List;

public class Event {
    private Double start_time;
    private Speaker speaker;
    private String title;
    private Room room;
    private ArrayList<User> users_lst;

    public Event(Double start, Speaker speaker_n, String topic, Room room_n){
        this.start_time = start;
        this.speaker = speaker_n;  //有没有办法不在constructor 定义speaker？
        this.title = topic;
        this.room = room_n;
        this.users_lst = new ArrayList<>();
    }

    public void remove_user(User user){
        if(users_lst.contains(user)){
            users_lst.remove(user);
        }
    }

    public void add_user(User user){
        users_lst.add(user);
    }

    public Double getStart_time() {
        return start_time;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker s){
        this.speaker = s;
    }

    public String getTitle() {
        return title;
    }

    public int getRoom_num() {
        return room.getRoom_num();
    }

    public Room getRoom() {
        return room;
    }

    public List<User> getUsers_lst() {
        return users_lst;
    }
}
