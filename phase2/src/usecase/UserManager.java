import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserManager {


    public boolean canCreateSpeaker(String username, Gateway g){
        /**
         * @Description: checks if username is unique
         */
        List<User> users = g.getUserList();
        for (User user : users){
            if (user.getUserName().equals(username)){
                return false;
            }
        }
        return true;
    }

    public void createSpeaker(String password, String name, Gateway g){
        /**
         * @Description: create a Speaker account
         */
        Speaker s = new Speaker(g.getNextUserId(), password, name);
        g.addUser(s);
    }

    public boolean canCreateAttendee(String username, Gateway g){
        /**
         * @Description: judge whether a username is available for a new Attendee account
         */
        return g.getUserByUserName(username) == null;
    }

    public void createAttendee(String password, String name ,Gateway g) {
        /**
         * @Description: create a Attendee account
         */
        Attendee a = new Attendee(g.getNextUserId(), password, name);
        g.addUser(a);
    }

    public boolean canAddEventToSpeaker(int eventID, int speakerId, Gateway g){
        /**
         * @Description: judge whether a Event is conflict with Events which a Speaker participate
         */
        Speaker s = g.getSpeakerById(speakerId);
        Event event = g.getEventById(eventID);
        if (s == null || event == null) {
            return false;
        }
        else {
            ArrayList<Integer> events = s.get_GivingEventList();
            for (Integer integer : events) {
                if (g.getEventById(integer).getStartTime().equals(event.getStartTime())) {
                    return false;
                }
            }
            return true;
        }
    }

    public void addEventToSpeaker(int eventId, int speakerId, Gateway g){
        /**
         * @Description: add a Event to a Speaker
         */
        removeEventFromSpeaker(eventId,g);
        g.getSpeakerById(speakerId).addGivingEvent(eventId);

    }

    private void removeEventFromSpeaker(int eventId, Gateway g) {
        /**
         * @Description: remove a Event to a Speaker
         */
        Event e = g.getEventById(eventId);
        g.getSpeakerById(e.getSpeakerId()).removeGivingEvent(eventId);
    }

    public boolean canSignUpForEvent(int eventId, int userId, Gateway g) {
        /**
         * @Description: judge whether a User is eligible to sign up an Event
         */
        Event e = g.getEventById(eventId);
        if (e == null | isExistingSpeaker(userId, g) |
                g.getRoomById(e.getRoomId()).getCapacity() <= e.getSingnedUserId().size()) {
            return false;
        }
        else {
            Attendee a = (Attendee) g.getUserById(userId);
            for (int i = 0; i <  a.getEventList().size(); i++) {
                if (g.getEventById(a.getEventList().get(i)).getStartTime().equals(e.getStartTime())){
                    return false;
                }
            }
            return true;
        }
    }

    public void addEventToAttendeeOrOrganizer(int eventId, int userId, Gateway g){
        /**
         * @Description: add an Event to Attendee or Organizer
         */
        if (!isExistingAttendee(userId, g)){
            g.getOrganizerById(userId).signUpEvent(eventId);
        }
        else{
            g.getAttendeeById(userId).signUpEvent(eventId);
        }
    }

    public void cancelEventToAttendeeOrOrganizer(int eventId, int userId, Gateway g) {
        /**
         * @Description: cancel an Event for Attendee or Organizer
         */
        if (!isExistingOrganizer(userId, g)) {
            g.getAttendeeById(userId).cancelEvent(eventId);
        } else {
            g.getOrganizerById(userId).cancelEvent(eventId);
        }
    }

    /**
     * Add event to the list of organized events of an organizer
     * @param eventID Event's ID
     * @param organizerID Organizer's ID
     * @param g Database
     */
    public void addEventToOrganizedList(int eventID, int organizerID, Gateway g){
        Organizer organizer = g.getOrganizerById(organizerID);
        organizer.AddCreatedEvent(eventID);
    }

    public void setPassword(int userId,String password, Gateway g) {
        /**
         * @Description: set password for an User
         */
        g.getUserById(userId).setPassword(password);
    }

    public String getUserName(int userId, Gateway g) {
        /**
         * @Description: get User name by userid
         */
        return g.getUserById(userId).getUserName();
    }

    public ArrayList<Integer> getOrganizerOrAttendeeEventList(int Id, Gateway g){
        /**
         * @Description: get Organizer or Attendee Event list
         */
        if (isExistingOrganizer(Id, g)){
            return g.getOrganizerById(Id).getEventList();
        }else {
            return g.getAttendeeById(Id).getEventList();
        }
    }

    public List<Integer> getOrganizedEventList(int organizerID, Gateway g){
        return g.getOrganizerById(organizerID).getCreatedEventList();
    }

    public List<Integer> getSpeakerEventList(int speakerID, Gateway g){
        /**
         * @Description: get Speaker Event list
         */
        return g.getSpeakerById(speakerID).get_GivingEventList();
    }

    public String getUserPassword(String username, Gateway g){
        /**
         * @Description: get User password by username
         */
        return g.getUserByUserName(username).getPassword();
    }

    public int getUserID(String username, Gateway g){
        /**
         * @Description: get User id by username
         */
        return g.getUserByUserName(username).getUserId();
    }

    public int getUserCategory(int id, Gateway g){
        /**
         * @Description: judge the User category, 0 means speaker, 1 means organizer, 2 means attendee
         */
        if (isExistingSpeaker(id, g)){
            // return 0 when id is a speaker
            return 0;
        }
        else if (g.getOrganizerById(id) != null){
            return 1;
        }
        else{
            return 2;
        }
    }


    public List<Integer> getListOfUsers(int userType, Gateway g){
        /**
         * @Description: get needed user category list. speaker = 0, organizer = 1, attendee = 2
         */
        List<User> users = g.getUserList();
        List<Integer> neededUsers = new ArrayList<>();
        for (User user : users){
            int userID = user.getUserId();
            if (getUserCategory(userID, g) == userType){
                neededUsers.add(userID);
            }
        }
        return neededUsers;
    }

    public String getUserString(int userID, Gateway g){
        /**
         * @Description: get User name and id
         */
        User user = g.getUserById(userID);
        return user.getUserName() + " (" + user.getUserId() + ")";
    }

    public boolean isSpeakerBusy(int speakerId, LocalDateTime time, Gateway g) {
        /**
         * @Description: judge whether a speaker is speaking
         */
        for (int eid :g.getSpeakerById(speakerId).get_GivingEventList()) {
            if (g.getEventById(eid).getStartTime().equals(time)) {
                return false;
            }
        }
        return true;
    }

    public boolean isExistingSpeaker(int userID, Gateway g){
        /**
         * @Description: judge a speaker is exist
         */
        return g.getSpeakerById(userID) != null;
    }

    public boolean isExistingUser(String username, Gateway g){
        /**
         * @Description: judge a User is exist
         */
        return g.getUserByUserName(username) != null;
    }

    public boolean isExistingAttendee(int userID, Gateway g){
        /**
         * @Description: judge an Attendee is exist
         */
        return g.getAttendeeById(userID) != null;
    }

    public boolean isExistingOrganizer(int userID, Gateway g){
        /**
         * @Description: judge an organizer is exist
         */
        return g.getOrganizerById(userID) != null;
    }

}