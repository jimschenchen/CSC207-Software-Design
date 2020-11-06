import java.util.ArrayList;
import java.util.List;

public class Event {
    private Double start_time;
    private String speaker;
    private String title;
    private int room_num;
    private List<String> users_lst;

    public Event(Double start, String speaker_n, String topic, int room_n, List<String> users){
        this.start_time = start;
        this.speaker = speaker_n;
        this.title = topic;
        this.room_num = room_n;
        this.users_lst = users;
    }

    public boolean can_remove(List<String> user){
        int a = 0;
        for (int i = 0; i < user.size(); i++) {
            if (users_lst.contains(user.get(i))) {
                a++;
            }
        }
        if (user.size() == a){
            return true;
        }
        return false;
    }

    public boolean remove_user(List<String> user) {
        if (can_remove(user) == false){
            return false;
        }
        for (int i = 0; i < user.size(); i++) {
            if (users_lst.contains(user.get(i))) {
                users_lst.remove(user.get(i));
            }
        }
        return true;
    }

    public void add_multi_users(List<String> user){
        for (int i = 0; i < user.size(); i ++){
            boolean flag = false;
            for (int j = 0; j < users_lst.size(); j ++){
                if (users_lst.get(j) == user.get(i)){
                    flag = true;
                }
            }
            if (flag == false){
                users_lst.add(user.get(i));
            }
        }
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

    public List<String> getUsers_lst() {
        return users_lst;
    }
}
