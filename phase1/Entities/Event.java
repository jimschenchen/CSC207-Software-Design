import java.util.ArrayList;
import java.util.List;

public class Event {
    private Double start_time;
    private String speaker;
    private String title;
    private int room_num;
    private List<User> users_lst;

    public Event(Double start, String speaker_n, String topic, int room_n, List<User> users){
        this.start_time = start;
        this.speaker = speaker_n;
        this.title = topic;
        this.room_num = room_n;
        this.users_lst = users;
    }

    public void remove(User user){
        users_lst.remove(user);
    }

    public void add(User user){
        users_lst.add(user);
    }

    public Double getStart_time() {
        return start_time;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getTitle() {
        return title;
    }

    public int getRoom_num() {
        return room_num;
    }

    public List<User> getUsers_lst() {
        return users_lst;
    }
}
