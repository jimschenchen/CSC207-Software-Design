import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_sv;

import javax.xml.crypto.Data;
import java.io.IOException;
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
    private Gateway gw = new Gateway();
    private int user; // store current logged in user's id


    /**
    * @Description: Initialization of Gateway and Databse
    * @Param: []
    * @return: void
    * @Date: 2020-11-14
    */
    public void init () throws IOException {
        shutDownHook();
        DataBase db = gw.init();
    }
    /**
    * @Description: Hook for listening the Shutdown of program and save all the data before exist0
    * @Param: []
    * @return: void
    * @Date: 2020-11-14
    */
    private void shutDownHook() {
        Runtime run = Runtime.getRuntime();
        run.addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    gw.termination(db);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Logs in a user.
     *
     * @param username User name of the user attempting to log in
     * @param password Password of the user input to attempt to log in
     * @return Returns -1 when login fails. When the login is successful,
     *         returns 0 when the user is a Speaker;
     *         returns 1 when the user is an Organizer;
     *         returns 2 when the user is an Attendee.
     */
    public int login(String username, String password){
        // String to int conversion
        if (db.getUserByUserName(username) != null){
            String dbPassword = um.getUserPassword(username, db);
            if (dbPassword.equals(password)){
                this.user = um.getUserID(username, db);
                return um.getUserCategory(this.user, db);
            }
        }
        // return false when ID doesn't exist or password does not match
        return -1;
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

//    /**
//     * Get current logged in user's name.
//     *
//     * @return Returns the user name.
//     */
//    public String getCurrentUserName(){
//        return um.getUserName(user, db);
//    }

    /**
     * Get user ID by user name.
     *
     * @param username User's name
     * @return Return user's ID
     */
    public String getUserIDbyUserName(String username){
        return Integer.toString(um.getUserID(username, db));
    }

    /**
     * Get user name by user ID.
     *
     * @param userID User's ID
     * @return Return user name
     */
    public String getUserNameByID(String userID){
        int uID = Integer.parseInt(userID);
        return um.getUserName(uID, db);
    }

    /**
     * Allow user to message all attendees in an event.
     *
     * @param eventID The event that all attendees and the speaker is in.
     * @param content The content of the message.
     * @return Return true if the message is sent successfully, false when input is invalid.
     */
    public boolean messageAllAttendeesInEvent(String eventID, String content){
        try{
            int eID = Integer.parseInt(eventID);
            if (db.getEventById(eID) != null){
                mm.message_allusers(eID, user, content, db);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Allow speaker to message all users in all events speaker is speaking in.
     *
     * @param content Content of the message
     * @return Return true if the message is sent successfully, else return false.
     */
    public boolean messageAllUsersInAllSpeakingEvents(String content){
        if (db.getSpeakerById(user) != null){
            mm.messageAllUsersInAllSpeakingEvents(user, content, db);
            return true;
        }
        return false;
    }

    /**
     * Allow user to message a specific attendee in an event
     *
     * @param eventID The event the attendees and speaker is in.
     * @param receiverID The receiver's ID.
     * @param content Content of the message.
     * @return Return true if the message is sent successfully, false when input is invalid.
     */
    public boolean messageOneSpecificUserInEvent(String eventID, String receiverID, String content){
        try{
            int eID = Integer.parseInt(eventID);
            int reID = Integer.parseInt(receiverID);
            if (db.getEventById(eID) != null && mm.canMessageAttendeeOfEvent(eID, reID, db)){
                mm.message_oneuser(user, reID, content, db);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    // send message to all speakers
    public boolean messageAllSpeakers(String content){
        if (mm.canMessageAllSpeakers(user, db)){
            mm.messageAllSpeakers(content, user, db);
            return true;
        }
        return false;
    }

    // send message to one speaker
    public boolean messageSpeaker(String receiverID, String content){
        try{
            int rID = Integer.parseInt(receiverID);
            if (mm.canMessageSpeaker(user, rID, db)){
                mm.message_oneuser(user, rID, content, db);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    public boolean messageAllAttendee(String content){
        if(db.getOrganizerById(user) != null){
            mm.messageAllAttendees(user, content, db);
            return true;
        }
        return false;
    }

    public boolean messageAttendee(String receiverID, String content){
        try{
            int rID = Integer.parseInt(receiverID);
            if (mm.canMessageAttendee(user, rID, db)){
                mm.message_oneuser(user, rID, content, db);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Reads the sent messages of the user currently logged in.
     *
     * @return List of Strings representing the messages the user sent.
     */
    public List<String> readSentMessages(){
        return mm.getSentMessageListByUserId(user, db);
    }

    /**
     * Reads the incoming messages of the user currently logged in.
     *
     * @return List of Strings representing the messages the user reveived.
     */
    public List<String> readReceivedMessages(){
        return mm.getReceivedMessageListByUserId(user, db);
    }

    /**
     * Reply to a specific message.
     *
     * @param messageIndex Position of the message (to identify which message it is replying)
     * @param content Content of the replying message.
     * @return Return true when the message is successfully sent, false otherwise.
     */
    public boolean replyMessage(String messageIndex, String content){
        try{
            int mIndex = Integer.parseInt(messageIndex);
            mm.replyMessage(content, user, mIndex, db);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

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
            if (em.canAddUserToEvent(user, eid, db) && um.canSignUpForEvent(eid, user, db)){ //need confirm
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

    /**
     * Creates a new speaker account into the system.
     *
     * @param userName User name of the new speaker.
     * @param password Password of the new speaker account.
     * @return Return true when the account is created successfully.
     *          Return false when the password is invalid,
     *          or when the user name is not unique.
     */
    public boolean addNewSpeaker(@NotNull String userName, @NotNull String password){
        if (password.trim().length() >=6 && um.canCreateSpeaker(userName, db)){
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
            int rID = db.getRoomByRoomNumber(rNumber); //getroombyroomnumber returns room object, you need call getRid.
            if (em.canCreateEvent(rNumber, sTime, db)){ // need to change param rid, time need to be localdatetime type
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
        List<Integer> allEvents = em.getEventList(db);
        List<String> events = new ArrayList<>();
        for (Integer eventID : allEvents){
            if (um.canSignUpForEvent(eventID, this.user, db)){//use canAddUserToEvent in eventmanager
                events.add(em.getStringOfEvent(eventID, db));
            }
        }
        return events;
    }

//    save data method
//    (all the methods need to pass gateway to use cases)
}
