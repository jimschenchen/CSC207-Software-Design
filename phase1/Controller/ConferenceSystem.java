import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class ConferenceSystem {

    // stores use cases and gateway
    private EventManager em = new EventManager();
    private MessageManager mm = new MessageManager();
    private RoomManager rm = new RoomManager();
    private UserManager um = new UserManager();
    private Gateway gw; // not sure what to do after storing gateway
    private DataBase db = new DataBase();
    private int user; // store current logged in user's id


    // login
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
     * Resets the password for the logged in user.
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

    //send message
    //speaker messages all attendees to his/her talk
    public boolean speakerMessageAllAttendees(){

    }

    //read messages of user
    public String readMessages(){
        List<Message> messages = db.getMessageListByUserId(user);

    }

    // reply message
    // subject to changes
    public boolean replyMessage(Message message, String content){
        mm.reply_message(message.getSenderId(), content, db);
    }

    // user sign up for an event
    // need the id of registering event from presenter
    // wrote assuming passing in String
    public boolean signUpForEvent(String eventID){
        try{
            int eid = Integer.parseInt(eventID);
            Event event = db.getEventById(eid);
            // check if the event exists, and user can sign up for event
            if (db.getEventById(eid) != null && um.canSignUpForEvent(event)){ // need confirm
                um.addEventToAttendeeOrOrganizer(eid, user, db);
                em.addUserToEvent(db.getUserById(user), event);
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

    // deregister from event
    public boolean withdrawFromEvent(int eventID){
        ArrayList<User> users = new ArrayList<>();
        User user = db.getUserById(this.user);
        users.add(user);
        if (em.can_remove(users)){
            em.remove_user(users);
            um.cancelEventToAttendeeOrOrganizer(eventID, this.user, db);
            return true;
        }
        return false;
    }

    // Speaker related methods
    // create a speaker account into system
    public boolean addNewSpeaker(String name, String password){
        // strip password and name to avoid extra white space
        // check if name is null and password long enough (>=6)
        if (name.trim().length() != 0 && password.trim().length() >=6){
            um.createSpeaker(password.trim(), name.trim(), db);
            return true;
        }
        return false;
    }

    // set speaker for an event
    // subject to change
    public boolean setSpeakerForEvent(String speakerID, String eventID){
        try{
            int sID = Integer.parseInt(speakerID);
            int eID = Integer.parseInt(eventID);
            if(um.canAddEventToSpeaker() && em.canSetSpeaker()){ // need confirm
                um.addEventToSpeaker(eID, sID, db);
                em.setSpeaker(db.getSpeakerById(sID), db.getEventById(eID));
                return true;
            }
            // return false when cannot add event to speaker
            return false;
        }
        catch(NumberFormatException nfe){
            // return false on invalid input
            return false;
        }
    }

    // create a new room into system
    // subject to changes
    public boolean addNewRoom(int roomNumber){
        int roomID = db.getNextRoomId();
        Room room = new Room(roomNumber, roomID);
        return rm.add_room(room);
    }

    // create a new event
    // subject to change
    public boolean newEvent(Double startTime, int speakerID, String topic, int roomNumber){
        int eventID = db.getNextEventId();
        Event event = new Event(startTime, eventID, speakerID, topic, roomNumber);
        return em.add_new_event(event);
    }

    // view current events
    public List<Event> viewCurrentEvents(){
        List<Event> events = db.getEventList();
    }

    // view current signed events
    // Question to lyanna and amina: do u want list of String or list of Event as return value
    public List<Event> viewSignedUpEvents(){
        um.getOrganizerOrAttendeeEventList(user, db);
    }

    // view events the speaker (logged in) are giving
    public List<Integer> viewSpeakingEvents(){
        return um.getSpeakerEventList(user, db);
    }

//    save data method
//    (all the methods need to pass gateway to use cases)
}
