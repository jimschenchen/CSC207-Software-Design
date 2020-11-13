import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class EventManager {
    private Double start_time;
    private int speaker_id;
    private String title;

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }

    public void setStart_time(Double start_time) {
        this.start_time = start_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean create_event(int room_id, int event_id, DataBase db){
        List<Event> all_event = db.getEventList();
        for (int i = 0; i < all_event.size(); i++){
            if(event_id == all_event.get(i).getEvent_id() && room_id == all_event.get(i).getRoomId()){
                Event event = new Event(this.start_time, event_id, this.speaker_id, this.title, room_id);
                    db.addEvent(event);
                    return true;
            }
            return false;
        }
        return false;
    }

    public boolean addEventToDB(Event new_event, DataBase db){
        boolean value = create_event(new_event.getRoomId(), new_event.getEvent_id(), db);
        if(value == true){
            db.addEvent(new_event);
            return true;
        }
        return false;
    }

    public void setSpeaker(Speaker speaker, Event event){
        event.setSpeaker_id(speaker.getUser_id());
    }

    public boolean addNewUser(User user, Event event){
        if (event.getSingned_userid().contains(user.getUser_id())){
            return false;
        }
        event.addSigned_userId(user);
        return true;
    }

    public boolean deleteUser(User user, Event event){
        if (event.getSingned_userid().contains(user.getUser_id())){
            if (event.remove_user(user.getUser_id()) == true){
                return true;
            }
            return false;
        }
        return false;
    }

    public Double getStart_time(Event event) {
        return event.getStart_time();
    }
    public int getSpeakerId(Event event) {
        return event.getSpeakerId();
    }

    public String getTitle(Event event) {
        return event.getTitle();
    }

    public int getRoomId(Event event) {
        return event.getRoomId();
    }

    public int getEvent_id(Event event) {
        return event.getEvent_id();
    }

    public List<User> getUserList(Event event, DataBase bd){
        ArrayList<User> all_User= new ArrayList<>();
        ArrayList<Integer> allUserID = event.getSingned_userid();
        for(int i = 0; i < allUserID.size(); i++){
            all_User.add(bd.getUserById(allUserID.get(i)));
        }
        return all_User;
    }

    public List<Event> getEventList(DataBase bd){
        ArrayList<Event> all_Events= new ArrayList<>();
        List<Event> events = bd.getEventList();
        for(Event e: events){
            all_Events.add(bd.getEventById(e.getEvent_id()));
        }
        return all_Events;
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