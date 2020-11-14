import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class EventManager {

    //don't need these instance
//    private int event_id;
//    private int speaker_id;
//    private String title;
//
//don't need these setter
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setEventId(int event_id) {
//        this.event_id = event_id;
//    }
//
//    public void setSpeakerId(int speaker_id) {
//        this.speaker_id = speaker_id;
//    }
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public boolean canCreateEvent(int room_id, LocalDateTime start, DataBase db){
        List<Event> all_event = db.getEventList();
        for (Event event : all_event) {
            if (room_id == event.getRoomId() && start.equals(event.getStart_time())) {
                return false;
            }
        }
        return true;
    }

    public void createEvent(LocalDateTime start, int speakerId, String title, int roomId, DataBase d){
        Event nEvent = new Event(start, d.getNextEventId(), speakerId, title, roomId);
        d.addEvent(nEvent);
    }

// this method is never used
//    public boolean addEventToDB(Event new_event, DataBase db){
//        boolean value = canCreateEvent(new_event.getRoomId(), new_event.getStart_time(), db);
//        if(value == true){
//            db.addEvent(new_event);
//            return true;
//        }
//        return false;
//    }

    public void setSpeaker(int speakerId, int eventId, DataBase d){
        d.getEventById(eventId).setSpeaker_id(speakerId);

    }

    public boolean canAddUserToEvent(int userid, int eventid, DataBase d) {
        if (d.getEventById(eventid) == null) {
            return false;
        }
        else {
            Event e = d.getEventById(eventid);
            if (e.getSingned_userid().contains(userid)
                    | d.getRoomById(e.getRoomId()).getCapacity() <= e.getSingned_userid().size()) {
                return false;
            }
            return true;
        }
    }


    public void addUserToEvent(int userId, int eventId, DataBase d){
        d.getEventById(eventId).add_user(userId);
    }


    public boolean canRemoveUser(int userid, int eventid, DataBase d) {
        if (d.getEventById(eventid) != null) {
            if (d.getEventById(eventid).getSingned_userid().contains(userid)) {
                return true;
            }
        }
        return false;
    }

    public void removeUser(int userId, int eventId, DataBase d) {
        d.getEventById(eventId).remove_user(userId);
    }



    public LocalDateTime getStart_time(Event event) {
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
        for (Integer integer : allUserID) {
            all_User.add(bd.getUserById(integer));
        }
        return all_User;
    }

//    public List<Event> getEventList(DataBase bd) {
//        ArrayList<Event> all_Events = new ArrayList<>();
//        List<Event> events = bd.getEventList();
//        for (Event e : events) {
//            all_Events.add(bd.getEventById(e.getEvent_id()));
//        }
//        return all_Events;
//    }

    public List<Integer> getEventList(DataBase db){
        List<Integer> allEvents = new ArrayList<>();
        List<Event> events = db.getEventList();
        for (Event event : events){
            allEvents.add(event.getEvent_id());
        }
        return allEvents;
    }

    public String getStringOfEvent(int eventID, DataBase db){
        Event event = db.getEventById(eventID);
        return "The event " + event.getTitle() +
                " with ID " + event.getEvent_id() +
                " by " + db.getSpeakerById(event.getSpeakerId()).getUserName() +
                " starts at " + event.getStart_time().format(getStartTimeFormatter()) +
                " takes place in " + db.getRoomById(event.getRoomId()).getRoom_num();
    }

    public DateTimeFormatter getStartTimeFormatter(){
        return this.formatter;
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