import javax.xml.crypto.Data;
import java.sql.DatabaseMetaData;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {

    // checks if username is unique
    public boolean canCreateSpeaker(String username, DataBase db){
        List<User> users = db.getUserList();
        for (User user : users){
            if (user.getUserName().equals(username)){
                return false;
            }
        }
        return true;
    }

    public void createSpeaker(String password, String name, DataBase d){
        Speaker s = new Speaker(d.getNextUserId(), password, name);
        d.addUser(s);
    }

    public boolean canCreateAttendee(String username, DataBase db){
        return db.getUserByUserName(username) == null;
    }

    public void createAttendee(String password, String name ,DataBase d) {
        Attendee a = new Attendee(d.getNextUserId(), password, name);
        d.addUser(a);
    }

    public boolean canAddEventToSpeaker(Event event, int speakerId, DataBase d){
        Speaker s = d.getSpeakerById(speakerId);
        if (s == null) {
            return false;
        }
        else {
            ArrayList<Integer> events = s.get_GivingEventList();
            for (Integer integer : events) {
                if (d.getEventById(integer).getStart_time().equals(event.getStart_time())) {
                    return false;
                }
            }
            return true;
        }
    }

    public void addEventToSpeaker(int eventId, int speakerId, DataBase d){
        d.getSpeakerById(speakerId).addGivingEvent(eventId);
    }

    public boolean canSignUpForEvent(int eventId, int userId, DataBase d) {
        Event e = d.getEventById(eventId);
        if (e == null | d.getSpeakerById(userId) != null |
                d.getRoomById(e.getRoomId()).getCapacity() <= e.getSingned_userid().size()) {
            return false;
        }
        else {
            Attendee a = (Attendee) d.getUserById(userId);
            for (int i = 0; i <  a.getEventList().size(); i++) {
                if (d.getEventById(a.getEventList().get(i)).getStart_time().equals(e.getStart_time())){
                    return false;
                }
            }
            return true;
        }
    }

    public void addEventToAttendeeOrOrganizer(int eventId, int userId, DataBase d){
        if (d.getAttendeeById(userId) == null){
            d.getOrganizerById(userId).signUpEvent(eventId);
        }
        else{
            d.getAttendeeById(userId).signUpEvent(eventId);
        }
    }

    public void cancelEventToAttendeeOrOrganizer(int eventId, int userId, DataBase d) {
        if (d.getOrganizerById(userId) == null) {
            d.getAttendeeById(userId).cancelEvent(eventId);
        } else {
            d.getOrganizerById(userId).cancelEvent(eventId);
        }
    }

    public void setPassword(int userId,String password, DataBase d) {
        d.getUserById(userId).setPassword(password);
    }

    public String getUserName(int userId,DataBase d) {
        return d.getUserById(userId).getUserName();
    }

    public ArrayList<Integer> getOrganizerOrAttendeeEventList(int Id, DataBase d){
        if (d.getAttendeeById(Id) == null){
            return d.getOrganizerById(Id).getEventList();
        }else {
            return d.getAttendeeById(Id).getEventList();
        }
    }

    public List<Integer> getSpeakerEventList(int speakerID, DataBase d){
        return d.getSpeakerById(speakerID).get_GivingEventList();
    }

    public String getUserPassword(String username, DataBase d){
        return d.getUserByUserName(username).getPassword();
    }

    public int getUserID(String username, DataBase db){
        return db.getUserByUserName(username).getUser_id();
    }

    public int getUserCategory(int id, DataBase d){
        if (d.getSpeakerById(id) != null){
            // return 0 when id is a speaker
            return 0;
        }
        else if (d.getOrganizerById(id) != null){
            // return 1 when id is an organizer
            return 1;
        }
        else{
            // return 2 when id is an attendee
            return 2;
        }
    }

    // get needed user category list
    // speaker = 0, organizer = 1, attendee = 2
    public List<Integer> getListOfUsers(int userType, DataBase db){
        List<User> users = db.getUserList();
        List<Integer> neededUsers = new ArrayList<>();
        for (User user : users){
            int userID = user.getUser_id();
            if (getUserCategory(userID, db) == userType){
                neededUsers.add(userID);
            }
        }
        return neededUsers;
    }

    public String getUserString(int userID, DataBase db){
        User user = db.getUserById(userID);
        return user.getUserName() + " (" + user.getUser_id() + ")";
    }

    public boolean isSpeakerBusy(int speakerId, LocalDateTime time, DataBase d) {
        for (int eid :d.getSpeakerById(speakerId).get_GivingEventList()) {
            if (d.getEventById(eid).getStart_time().equals(time)) {
                return false;
            }
        }
        return true;
    }

}