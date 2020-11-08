import java.util.List;

public class ConferenceSystem {

//    stores use cases and gateway
    EventManager em = new EventManager();
    MessageManager mm = new MessageManager();
    RoomManager rm = new RoomManager();
    UserManager um = new UserManager();
    Gateway gw;

//    send messages, read messages
    public boolean sendMessages(){

    }

    public String readMessages(){

    }

//    sign up for a event
    public boolean signUpForEvent(User user, Event event){

    }

//    change password
    public boolean resetPassword(String newPassword){

    }

//    create a speaker account into system
    public boolean addNewSpeaker(String password){

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
