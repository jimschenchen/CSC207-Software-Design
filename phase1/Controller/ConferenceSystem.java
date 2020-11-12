import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class ConferenceSystem {

    // stores use cases and gateway
    EventManager em = new EventManager();
    MessageManager mm = new MessageManager();
    RoomManager rm = new RoomManager();
    UserManager um = new UserManager();
    Gateway gw; // not sure what to do after storing gateway
    DataBase db = new DataBase();
    int user; // store current user id


    // login
    // it would be even better if presenter can pass in int instead of string, but this is also completely fine
    public boolean login(String uid, String password){
        int iuid;
        try {
            // String to int conversion
            iuid = Integer.parseInt(uid);
        }
        catch(NumberFormatException nfe) {
            // when the input is not a valid number
            return false;
        }
        if (um.canLogin(iuid, password)){
            um.login(iuid, password);
            this.user = iuid;
            return true;
        }
        return false; // return false when login fail
    }

    // change password
    // assuming users cannot reset passwords before login
    public boolean resetPassword(String newPassword){
        // passwords should always be 6 characters or longer
        if (newPassword.length() >= 6){
            um.setPassword(newPassword);
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
        mm.reply_message(message, content);
    }

    // user sign up for an event
    // need the id of registering event from presenter (either int or String
    // wrote assuming passing in String
    public boolean signUpForEvent(String eventID){
        try{
            int eid = Integer.parseInt(eventID);
            Event event = db.getEventById(eid);
            // check if the event exists, and user can sign up for event
            if (db.getEventById(eid) != null && um.canSignUpForEvent(event)){
                um.signUpForEvent(event);
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

    // !!!! need change !!!!
    // deregister from event
    public boolean withdrawFromEvent(User user, int eventID){
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        if (em.can_remove(users)){
            em.remove_user(users);
            return true;
        }
        return false;
    }

    //create a speaker account into system
    // subject to changes
    public boolean addNewSpeaker(String name, String password){
        int uid = db.getNextUserId();
        Speaker speaker = new Speaker(uid, password, name);
        um.newSpeaker(speaker);
        return true;
    }

    // create a new room into system
    // subject to changes
    public boolean addNewRoom(int roomNumber){
        int roomID = db.getNextRoomId();
        Room room = new Room(roomNumber, roomID);
        return rm.add_room(room);
    }

    // create a new event
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
    public List<Event> viewSignedUpEvents(){
    }

    // view events the speaker are giving.
    public List<Event> viewSpeakingEvents(){}

//    save data method
//    (all the methods need to pass gateway to use cases)
}
