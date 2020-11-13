import sun.awt.HKSCS;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Event {
    private Double start_time; // think about time.localdatetime.
    private int speaker_id;
    private String title;
    private int room_id;
    private ArrayList<Integer> singned_userid;
    private int event_id;

    public Event(Double start, int event_id, int speaker_id, String topic, int room_n){
        this.start_time = start;
        this.speaker_id = speaker_id;
        this.title = topic;
        this.room_id = room_n;
        this.singned_userid = new ArrayList<>();
        this.event_id = event_id;
    }

    public boolean remove_user(int user_id){
        if(singned_userid.contains(user_id)){
            singned_userid.remove(user_id);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean add_user(int user_id){
        if(singned_userid.contains(user_id)) {
            return false;
        }
        else {
            singned_userid.add(user_id);
            return true;
        }
    }

    public Double getStart_time() {
        return start_time;
    }

    public int getSpeakerId() {
        return speaker_id;
    }

    public String getTitle() {
        return title;
    }

    public int getRoomId() {
        return room_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public ArrayList<Integer> getSingned_userid() {
        return singned_userid;
    }

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "speaker_id=" + speaker_id +
                ", event_id=" + event_id +
                '}';
    }

    public void addSigned_userId(User user) {
        this.singned_userid.add(user.getUser_id());
    }
}
