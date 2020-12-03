package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import usecase.*;
import gateway.Gateway;

public class ConferenceSystem {

    /**
     * The ConferenceSystem Class.
     */
    private EventManager em = new EventManager();
    private MessageManager mm = new MessageManager();
    private RoomManager rm = new RoomManager();
    private UserManager um = new UserManager();
    private Gateway gw = new Gateway();
    private int user;
    private MessagingSystem ms = new MessagingSystem();
    private ViewingSystem vs = new ViewingSystem();
//
//    /**
//    * @Description: Initialization of Gateway and Databse
//    * @Param: []
//    * @return: void
//    * @Date: 2020-11-14
//    */
//    public void init () throws IOException {
//        shutDownHook();
//        /** initial of database */
//        gw.init();\
//    }
//    /**
//    * @Description: Hook for listening the Shutdown of program and save all the data before exist0
//    * @Param: []
//    * @return: void
//    * @Date: 2020-11-14
//    */
//    private void shutDownHook() {
//        Runtime run = Runtime.getRuntime();
//        run.addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("System: ShutDownHook");
//                    gw.termination(gw);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Sign up a new attendee to the system.
     *
     * @param username User name of the new attendee. username should be unique.
     * @param password Password of the new attendee account. Password should be at least 6 characters long.
     * @return Return true if the account is created successfully, false otherwise.
     */
    public boolean signup(String username, String password){
        if (um.canCreateAttendee(username, gw) && password.length() >= 6){
            um.createAttendee(password, username, gw);
            return true;
        }
        return false;
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
        if (um.isExistingUser(username, gw)){
            String dbPassword = um.getUserPassword(username, gw);
            if (dbPassword.equals(password)){
                this.user = um.getUserID(username, gw);
                ms.setUser(this.user);
                vs.setUser(this.user);
                return um.getUserCategory(this.user, gw);
            }
        }
        return -1;
    }

    /**
     * Resets the password for the logged in user. Checks if the password is 6 characters or longer.
     *
     * @param newPassword New password input by user.
     * @return Returns True when the password is valid and is changed. False otherwise.
     */
    public boolean resetPassword(String newPassword){
        // passwords should always be 6 characters or longer
        if (newPassword.length() >= 6){
            um.setPassword(user, newPassword, gw);
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
        return Integer.toString(um.getUserID(username, gw));
    }

    /**
     * Get user name by user ID.
     *
     * @param userID User's ID
     * @return Return user name
     */
    public String getUserNameByID(String userID){
        int uID = Integer.parseInt(userID);
        return um.getUserName(uID, gw);
    }

    /**
     * Allow user to message all attendees in an event.
     *
     * @param eventID The event that all attendees and the speaker is in.
     * @param content The content of the message.
     * @return Return true if the message is sent successfully, false when input is invalid.
     */
    public boolean messageAllAttendeesInEvent(String eventID, String content){
        return ms.messageAllAttendeesInEvent(eventID, content, gw);
    }

    /**
     * Allow speaker to message all users in all events speaker is speaking in.
     *
     * @param content Content of the message
     * @return Return true if the message is sent successfully, else return false.
     */
    public boolean messageAllUsersInAllSpeakingEvents(String content){
        return ms.messageAllUsersInAllSpeakingEvents(content, gw);
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
        return ms.messageOneSpecificUserInEvent(eventID, receiverID, content, gw);
    }

    /**
     * Sends a message to all speakers at once. Only Organizers can perform such action.
     *
     * @param content Content of the message.
     * @return Return true if messages are sent successfully. False if the logged in user is not an organizer.
     */
    public boolean messageAllSpeakers(String content){
        return ms.messageAllSpeakers(content, gw);
    }

    /**
     * Sends a message to a specific speaker.
     *
     * @param receiverID The receiver's (speaker) ID.
     * @param content Content of the message
     * @return Return true if the message is sent successfully. False if the input(ID) is invalid, or the user
     * is not allowed to message the speaker.
     */
    public boolean messageSpeaker(String receiverID, String content){
        return ms.messageSpeaker(receiverID, content, gw);
    }

    /**
     * Sends a message to all attendees in the system. This action can only be performed by an organizer.
     *
     * @param content Content of the message.
     * @return Return true if the messages are successfully sent. False if the logged in sender is not allowed to
     * perform this action.
     */
    public boolean messageAllAttendee(String content){
        return ms.messageAllAttendee(content, gw);
    }

    /**
     * Sends a message to an attendee.
     *
     * @param receiverID Message receiver's ID.
     * @param content Content of the message.
     * @return Return true if the message is sent successfully. False if the user is not allowed to message this
     * attendee, or input is invalid.
     */
    public boolean messageAttendee(String receiverID, String content){
        return ms.messageAttendee(receiverID, content, gw);
    }

    /**
     * Reads the sent messages of the user currently logged in.
     *
     * @return List of Strings representing the messages the user sent.
     */
    public List<String> readSentMessages(){
        return ms.readSentMessages(gw);
    }

    /**
     * Reads the incoming messages of the user currently logged in.
     *
     * @return List of Strings representing the messages the user reveived.
     */
    public List<String> readReceivedMessages(){
        return ms.readReceivedMessages(gw);
    }

    /**
     * Reply to a specific message.
     *
     * @param messageIndex Position of the message (to identify which message it is replying)
     * @param content Content of the replying message.
     * @return Return true when the message is successfully sent, false if the user is not allowed to reply to the
     * message.
     */
    public boolean replyMessage(String messageIndex, String content){
        return ms.replyMessage(messageIndex, content, gw);
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
            if (em.canAddUserToEvent(user, eid, gw) && um.canSignUpForEvent(eid, user, gw)){ //need confirm
                um.addEventToAttendeeOrOrganizer(eid, user, gw);
                em.addUserToEvent(user, eid, gw);
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
     * Allow current logged in attendee/organizer to cancel their enrollment in an event.
     *
     * @param eventID ID of the event they want to deregister from.
     * @return Return True when the user has successful cancelled their enrollment in the event.
     */
    public boolean cancelEnrollmentInEvent(String eventID){
        try{
            int eid = Integer.parseInt(eventID);
            if (em.canRemoveUser(this.user ,eid, gw)){
                em.removeUser(this.user, eid, gw);
                um.cancelEventToAttendeeOrOrganizer(eid, this.user, gw);
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
    public boolean addNewSpeaker(String userName, String password){
        if (password.trim().length() >=6 && userName.trim().length() > 0 && um.canCreateSpeaker(userName, gw)){
            um.createSpeaker(password.trim(), userName.trim(), gw);
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
            if(um.canAddEventToSpeaker(eID, sID, gw)){
                um.addEventToSpeaker(eID, sID, gw);
                em.setSpeaker(sID, eID, gw);
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
     * @param roomNumber Room number of the new room.
     * @return Return true when successfully added a new room, false otherwise.
     */
    public boolean addNewRoom(String roomNumber){
        try{
            if (rm.canAddRoom(roomNumber, gw)){
                rm.add_room(roomNumber, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Creates a new event into the system.
     *
     * @param startTime Start time of the event. In the format of "yyyy-MM-dd HH". Example: "2020-11-14 18"
     * @param speakerID ID of speaker giving the event.
     * @param topic Topic/title of the event.
     * @param roomNumber Room number of the location of this event.
     * @return Return true if successfully created a new event into the system, false otherwise.
     */
    public boolean newEvent(String type1, String type2, String startTime, String endTime, String speakerID,
                            String topic, String roomNumber, String capacity){
        try{
            int type3 = Integer.parseInt(type1);
            int type4 = Integer.parseInt(type2);
            LocalDateTime sTime = LocalDateTime.parse(startTime, em.getTimeFormatter());
            LocalDateTime eTime = LocalDateTime.parse(endTime, em.getTimeFormatter());
            int sID = Integer.parseInt(speakerID);
            int rID = rm.getRoomIDbyRoomNumber(roomNumber, gw);
            int cap = Integer.parseInt(capacity);
            if (um.isExistingSpeaker(sID, gw) && em.canCreateEvent(rID, sTime, eTime, cap, gw) &&
                    um.isSpeakerBusy(sID,sTime, gw)){

                int eventID = em.createEvent(type3, type4, sID, sTime, eTime, topic, rID, cap, gw);
                um.addEventToOrganizedList(eventID, user, gw);
                um.addEventToSpeaker(eventID, sID, gw);
                return true;
            }
            return false; // return false when unsuccessful
        }
        catch(DateTimeParseException | NullPointerException ex){
            return false; // return false on invalid input
        }
    }

    public boolean cancelEvent(String eventID){
        try{
            int eID = Integer.parseInt(eventID);
            if (um.canCancelEvent(user, eID, gw) && em.canCancelEvent(eID, gw)){
                um.cancelEvent(eID, gw);
                em.cancelEvent(eID, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Return all events that are currently scheduled.
     *
     * @return List of Strings of the events
     */
    public List<String> viewEvents(){
        return vs.viewEvents(gw);
    }

    /**
     * Return a list of events that the current logged in user has signed up for.
     *
     * @return List of Strings of the events
     */
    public List<String> viewSignedUpEvents(){
        return vs.viewSignedUpEvents(gw);
    }

    /**
     * Return a list of events that the current logged in organizer has organized
     *
     * @return List of Strings of the events
     */
    public List<String> viewOrganizedEvents(){
        return vs.viewOrganizedEvents(gw);
    }

    /**
     * Return a list of events that the current Speaker is going to speak for.
     *
     * @return List of Strings of the events
     */
    public List<String> viewSpeakingEvents(){
        return vs.viewSpeakingEvents(gw);
    }

    /**
     * Return a list of events that the current logged in attendee can sign up for.
     *
     * @return List of Strings of the events
     */
    public List<String> viewCanSignUpEvents(){
        return vs.viewCanSignUpEvents(gw);
    }

    /**
     * View all attendees who are registered in one of the current speaker's speaking events. Without duplicates.
     *
     * @return Return a list of Strings that represent all attendees of all the events the speaker is speaking for.
     * Every attendee is represented by a string formated as follows: "UserName (userID)"
     */
    public List<String> viewAttendeesInSpeakingEvents(){
        return vs.viewAttendeesInSpeakingEvents(gw);
    }

    /**
     * View all attendees in the system.
     *
     * @return Return a list of Strings that represent all attendees in the system.
     * Every attendee is represented by a string formatted as follows: "UserName (userID)"
     */
    public List<String> viewAllAttendees(){
        return vs.viewAllAttendees(gw);
    }

    /**
     * View all speakers in the system.
     *
     * @return Return a list of Strings that represent all speakers in the system.
     * Every speaker is represented by a string formatted as follows: "UserName (userID)"
     */
    public List<String> viewAllSpeakers(){
        return vs.viewAllSpeakers(gw);
    }

    /**
     * View all rooms in the system.
     *
     * @return Return a list of strings that represent all rooms in the system.
     * Every room is represented by a string formatted as follows: "RoomName/Number (RoomID)"
     */
    public List<String> viewAllRooms(){
        return vs.viewAllRooms(gw);
    }
}
