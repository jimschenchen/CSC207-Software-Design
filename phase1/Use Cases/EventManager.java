import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class EventManager {
//    private Event event;

    public boolean create_event(Event new_event, DataBase db){
        List<Event> all_event = db.getEventList();
        boolean flag = true;
        for (int i = 0; i < all_event.size(); i++){
            if(new_event.getEvent_id() == all_event.get(i).getEvent_id() && new_event.getRoomId() == all_event.get(i).getRoomId()){
                flag = false;
            }
        }
        if(flag == true){
            return true;
        }
        return false;
    }

    public boolean addEventToDB(Event new_event, DataBase db){
        boolean value = create_event(new_event, db);
        if(value == true){
            db.addEvent(new_event);
            return true;
        }
        return false;
    }

    public void setSpeaker(Speaker speaker, Event event){
        event.setSpeaker_id(speaker.getUser_id());
    }




    public boolean can_remove(int userId, int eventId, DataBase d) {
        return d.getEventById(eventId).getSingned_userid().contains(userId);

    }



    public void addUserToEvent(int userId, int eventId, DataBase d){
        d.getEventById(eventId).add_user(userId);
    }

    public void remove_user(int userId, int eventId, DataBase d) {
        d.getEventById(eventId).remove_user(userId);
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

    public List<Event> getEventList(DataBase bd) {
        ArrayList<Event> all_Events = new ArrayList<>();
        List<Event> events = bd.getEventList();
        for (Event e : events) {
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