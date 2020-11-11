import java.util.ArrayList;
import java.util.List;

public class ConferenceSystem {

    // stores use cases and gateway
    EventManager em = new EventManager();
    MessageManager mm = new MessageManager();
    RoomManager rm = new RoomManager();
    UserManager um = new UserManager();
    Gateway gw; // not sure what to do after storing gateway


    // login
    // it would be even better if presenter can pass in int instead of string, but this is also completely fine
    public boolean login(String uid, String password){
        int iuid = Integer.parseInt(uid);
        // length of id must be at least 1
        if (uid.length() == 0){
            return false;
        }
        if (um.canLogin(iuid, password)){
            um.login(iuid, password);
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

    //read messages
    public String readMessages(){

    }

    // reply message
    public boolean replyMessage(Message message, String content){
        mm.reply_message(message, content);
    }

    // sign up for a event
    public boolean signUpForEvent(User user, Event event){

    }

    // deregister from event
    public boolean withdrawFromEvent(User user, Event event){
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        if (em.can_remove(users)){
            em.remove_user(users);
            return true;
        }
        return false;
    }

    //create a speaker account into system
    public boolean addNewSpeaker(String name, String password){
    }

//    create a new room into system
    public boolean addNewRoom(int capacity){

    }

//    create an event.
    public boolean newEvent(Double startTime, String speaker, String topic, int room, List<User> attendees){

    }

//    view current events
    public List<Event> viewCurrentEvents(){
    }

//    view current signed events
    public List<Event> viewSignedUpEvents(){
    }

//    view events the speaker are giving.
    public List<Event> viewSpeakingEvents(){}

//    save data method
//    (all the methods need to pass gateway to use cases)
}
