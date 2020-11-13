import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_sv;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ConferenceSystem {

    // stores use cases and gateway
    private EventManager em = new EventManager();
    private MessageManager mm = new MessageManager();
    private RoomManager rm = new RoomManager();
    private UserManager um = new UserManager();
    private DataBase db = new DataBase();
    private int user; // store current logged in user's id


    /**
     * Logs in a user.
     *
     * @param id ID of the user attempting to log in
     * @param password Password of the user input to attempt to log in
     * @return Returns -1 when login fails. When the login is successful,
     *         returns 0 when the user is a Speaker;
     *         returns 1 when the user is an Organizer;
     *         returns 2 when the user is an Attendee.
     */
    public int login(String id, String password){
        try {
            // String to int conversion
            int uid = Integer.parseInt(id);
            if (db.getUserById(uid) != null){
                String dbPassword = um.getUserPassword(uid, db);
                if (dbPassword.equals(password)){
                    this.user = uid;
                    return um.getUserCategory(uid, db);
                }
            }
            // return false when ID doesn't exist or password does not match
            return -1;
        }
        catch(NumberFormatException nfe) {
            // when the input is not a valid number
            return -1;
        }
    }

    // change password
    // assuming users cannot reset passwords before login
    /**
     * Resets the password for the logged in user. Checks if the password is 6 characters or longer.
     *
     * @param newPassword New password input by user.
     * @return Returns True when the password is valid and is changed. False otherwise.
     */
    public boolean resetPassword(String newPassword){
        // passwords should always be 6 characters or longer
        if (newPassword.length() >= 6){
            um.setPassword(user, newPassword, db);
            return true;
        }
        return false;
    }

    // get user name
    /**
     * Get current logged in user's name.
     *
     * @return Returns the user name.
     */
    public String getCurrentUserName(){
        return um.getUserName(user, db);
    }

    //send message
    //speaker messages all attendees to his/her talk
    public boolean speakerMessageAllAttendees(){

    }

    //read messages of user
    public List<String> readMessages(){

    }

    // reply message
    // subject to changes
    public boolean replyMessage(Message message, String content){
        mm.reply_message(content, message.getSenderId(), db);
    }

    // user sign up for an event

    /**
     * Allow current logged in user to sign up to an event.
     *
     * @param eventID ID of the event signing up for.
     * @return Return True if user is successfully signed up for the event, false otherwise.
     */
    public boolean signUpForEvent(String eventID) {
        try{
            int eid = Integer.parseInt(eventID);
            // check if the event exists, and user can sign up for event
            if (em.canAddUserToEvent(eid, db) && um.canSignUpForEvent(eid, user, db)){ // need confirm
                um.addEventToAttendeeOrOrganizer(eid, user, db);
                em.addUserToEvent(user, eid, db);
                return true;
            }
            // return false when event doesn't exist or user cannot sign up for event
            return false;
        }
        catch(NumberFormatException nfe){
            // return false when input is invalid
            return false;
        }
    }


    /**
     * Allow current logged in attendee to cancel their enrollment in an event.
     *
     * @param eventID ID of the event they want to deregister from.
     * @return Return True when the user has successful cancelled their enrollment in the event.
     */
    public boolean cancelEnrollmentInEvent(String eventID){
        try{
            int eid = Integer.parseInt(eventID);
            if (em.canRemoveUser(this.user ,eid, db)){
                em.removeUser(this.user, eid, db);
                um.cancelEventToAttendeeOrOrganizer(eid, this.user, db);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }

    }

    // create a speaker account into system
    public boolean addNewSpeaker(@NotNull String userName, @NotNull String password){
        // strip password and name to avoid extra white space
        // check if password long enough (>=6)
        if (password.trim().length() >=6){
            um.createSpeaker(password.trim(), userName.trim(), db);
            return true;
        }
        return false;
    }

    /**
     * Set the speaker of an event.
     *
     * @param speakerID New Speaker's ID
     * @param eventID The event's ID
     * @return Return True when the speaker is assigned to the event successfully, false otherwise.
     */
    public boolean setSpeakerForEvent(String speakerID, String eventID){
        try{
            int sID = Integer.parseInt(speakerID);
            int eID = Integer.parseInt(eventID);
            if(um.canAddEventToSpeaker(db.getEventById(eID), sID, db)){
                um.addEventToSpeaker(eID, sID, db);
                em.setSpeaker(sID, eID, db);
                return true;
            }
            return false; // return false when cannot add event to speaker
        }
        catch(NumberFormatException nfe){
            return false; // return false on invalid input
        }
    }

    /**
     * Creates a new room into the system.
     *
     * @param roomNumber The room number of the new room.
     * @return Return true when successfully added a new room, false otherwise.
     */
    public boolean addNewRoom(String roomNumber){
        try{
            int rNumber = Integer.parseInt(roomNumber);
            if (rm.canAddRoom(rNumber, db)){
                rm.add_room(rNumber, db);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    // create a new event
    // subject to change
    public boolean newEvent(String startTime, String speakerID, String topic, String roomNumber){
        try{
            Double sTime = Double.parseDouble(startTime);
            int sID = Integer.parseInt(speakerID);
            int rNumber = Integer.parseInt(roomNumber);
            if (em.canCreateEvent(rNumber, sTime, db)){ // need to change param rid
                em.createEvent(rNumber, sTime, db); // need to change param rid
                return true;
            }
            return false; // return false when unsuccessful
        }
        catch(NumberFormatException nfe){
            return false; // return false on invalid input
        }
    }

    /**
     * Return all events that are currently scheduled.
     *
     * @return List of Strings of the events
     */
    public List<String> viewEvents(){
        List<Integer> events = em.getEventList(db);
        List<String> sEvents = new ArrayList<>();
        for (Integer eventID : events){
            sEvents.add(em.getStringOfEvent(eventID, db));
        }
        return sEvents;
    }

    /**
     * Return a list of events that the current logged in user has signed up for, or has organized, depending
     * on the user type.
     *
     * @return List of Strings of the events
     */
    public List<String> viewSignedUpOrOrganizedEvents(){
        List<Integer> events = um.getOrganizerOrAttendeeEventList(user, db);
        List<String> sEvents = new ArrayList<>();
        for (Integer eventID : events){
            sEvents.add(em.getStringOfEvent(eventID, db));
        }
        return sEvents;
    }

    /**
     * Return a list of events that the current Speaker is going to speak for.
     *
     * @return List of Strings of the events
     */
    public List<String> viewSpeakingEvents(){
        List<Integer> events = um.getSpeakerEventList(user, db);
        List<String> sEvents = new ArrayList<>();
        for (Integer eventID : events){
            sEvents.add(em.getStringOfEvent(eventID, db));
        }
        return sEvents;
    }

    /**
     * Return a list of events that the current logged in attendee can sign up for.
     *
     * @return List of Strings of the events
     */
    public List<String> viewCanSignUpEvents(){
        List<Integer> allEvents = em.getEventList();
        List<String> events = new ArrayList<>();
        for (Integer eventID : allEvents){
            if (um.canSignUpForEvent(eventID, this.user, db)){
                events.add(em.getStringOfEvent(eventID, db));
            }
        }
        return events;
    }

//    save data method
//    (all the methods need to pass gateway to use cases)
}
