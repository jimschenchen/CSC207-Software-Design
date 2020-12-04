package controller;

import java.util.List;

import usecase.*;
import gateway.GatewayFacade;

public class ConferenceSystem {

    /**
     * The ConferenceSystem Class.
     */
    private RoomManager rm = new RoomManager();
    private UserManager um = new UserManager();
    private GatewayFacade gw = new GatewayFacade();
    private int user;
    private MessagingSystem ms = new MessagingSystem();
    private ViewingSystem vs = new ViewingSystem();
    private EventManagementSystem ems = new EventManagementSystem();
    private EventEnrollmentSystem ees = new EventEnrollmentSystem();
//
//    /**
//    * @Description: Initialization of Gateway and Database
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

    private boolean checkValidPassword(String password){
        return password.length() >= 6;
    }

    public boolean createAttendee(String userName, String password){
        /**
         * create an attendee by logged in user. Only organizers can perform this action.
         *
         * @param username User name of the new attendee. username should be unique.
         * @param password Password of the new attendee account. Password should be at least 6 characters long.
         * @return Return true if the Attendee is created successfully, false otherwise.
         */
        if (um.isExistingOrganizer(user, gw)){
            return signup(userName, password);
        }
        return false;
    }

    public boolean createSpeaker(String userName, String password){
        /**
         * Creates a new speaker account into the system. This action can only be performed by an organizer.
         *
         * @param userName User name of the new speaker.
         * @param password Password of the new speaker account.
         * @return Return true when the account is created successfully.
         *          Return false when the password is invalid,
         *          or when the user name is not unique.
         */
        if (checkValidPassword(password) && um.canCreateUser(userName, gw) && um.isExistingOrganizer(user, gw)){
            um.createSpeaker(password.trim(), userName.trim(), gw);
            return true;
        }
        return false;
    }

    public boolean createVipUser(String userName, String password){
        /**
         * create a VIP. This action can only be performed by an organizer.
         *
         * @param username User name of the new VIP. username should be unique.
         * @param password Password of the new VIP account. Password should be at least 6 characters long.
         * @return Return true if the VIP is created successfully, false otherwise.
         */
        if (checkValidPassword(password) && um.canCreateUser(userName, gw) && um.isExistingOrganizer(user, gw)){
            um.createVIP(password.trim(), userName.trim(), gw);
            return true;
        }
        return false;
    }

    /**
     * Sign up a new attendee to the system.
     *
     * @param username User name of the new attendee. username should be unique.
     * @param password Password of the new attendee account. Password should be at least 6 characters long.
     * @return Return true if the account is created successfully, false otherwise.
     */
    public boolean signup(String username, String password){
        if (um.canCreateUser(username, gw) && checkValidPassword(password)){
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
     *         returns 2 when the user is an Attendee/VIP.
     */
    public int login(String username, String password){
        if (um.isExistingUser(username, gw)){
            String dbPassword = um.getUserPassword(username, gw);
            if (dbPassword.equals(password)){
                this.user = um.getUserID(username, gw);
                ms.setUser(this.user);
                vs.setUser(this.user);
                ems.setUser(this.user);
                ees.setUser(this.user);
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
     * @return List of Strings representing the messages the user received.
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
        return ees.signUpForEvent(eventID, gw);
    }

    public boolean signUpForEventWaitList(String eventId) {
        return ees.signUpForEventWaitList(eventId, gw);
    }

    /**
     * Allow current logged in attendee/organizer to cancel their enrollment in an event.
     *
     * @param eventId ID of the event they want to deregister from.
     * @return Return True when the user has successful cancelled their enrollment in the event.
     */
    public boolean cancelEnrollmentInEvent(String eventId){
        return ees.cancelEnrollmentInEvent(eventId, gw);
    }

    public boolean removeEventFromWaitList(String eventId) {
        return ees.removeEventFromWaitList(eventId, gw);
    }

    /**
     * Set the speaker of an event.
     *
     * @param speakerID New Speaker's ID
     * @param eventID The event's ID
     * @return Return True when the speaker is assigned to the event successfully, false otherwise.
     */
    public boolean changeSpeakerForOneSpeakerEvent(String speakerID, String eventID){
        return ems.changeSpeakerForOneSpeakerEvent(speakerID, eventID, gw);
    }

    public boolean addSpeakerToMultiSpeakerEvent(String speakerId, String eventId) {
        return ems.addSpeakerToMultiSpeakerEvent(speakerId, eventId, gw);
    }

//    /**
//     * Creates a new event into the system.
//     *
//     * @param startTime Start time of the event. In the format of "yyyy-MM-dd HH". Example: "2020-11-14 18"
//     * @param speakerID ID of speaker giving the event.
//     * @param topic Topic/title of the event.
//     * @param roomNumber Room number of the location of this event.
//     * @return Return true if successfully created a new event into the system, false otherwise.
//     */
//    public boolean newEvent(String type01, String type02, String startTime, String endTime, @Nullable String speakerID,
//                            String topic, String roomNumber, String capacity){
//        try{
//            int type1 = Integer.parseInt(type01);
//            int type2 = Integer.parseInt(type02);
//            LocalDateTime sTime = LocalDateTime.parse(startTime, em.getTimeFormatter());
//            LocalDateTime eTime = LocalDateTime.parse(endTime, em.getTimeFormatter());
//            int rID = rm.getRoomIDbyRoomNumber(roomNumber, gw);
//            int cap = Integer.parseInt(capacity);
//            if (speakerID.contains(",")) {
//                ArrayList<Integer> sID = new ArrayList<>();
//                StringTokenizer token = new StringTokenizer(speakerID,",");
//                while (token.hasMoreElements()) {
//                    sID.add(Integer.parseInt(token.nextToken()));
//                    if (um.isExistingSpeaker(sID, gw) && em.canCreateEvent(rID, sTime, eTime, cap, gw) &&
//                            um.isSpeakerBusy(sID,sTime, eTime, gw)) {
//
//                        int eventID = em.createEvent(type1, type2, sID, sTime, eTime, topic, rID, cap, gw);
//                        um.addEventToOrganizedList(eventID, user, gw);
//                        for (int speaker : sID){
//                            um.addEventToSpeaker(eventID, speaker, gw);}
//                        return true;
//                    }
//                }
//            }
//            else {
//                int sID = Integer.parseInt(speakerID);
//                if (um.isExistingSpeaker(sID, gw) && em.canCreateEvent(rID, sTime, eTime, cap, gw) &&
//                        um.isSpeakerBusy(sID, sTime, eTime, gw)) {
//
//                    int eventID = em.createEvent(type1, type2, sID, sTime, eTime, topic, rID, cap, gw);
//                    um.addEventToOrganizedList(eventID, user, gw);
//                    um.addEventToSpeaker(eventID, sID, gw);
//                    return true;
//                }
//            }
//
//            return false; // return false when unsuccessful
//        }
//        catch(DateTimeParseException | NullPointerException ex){
//            return false; // return false on invalid input
//        }
//    }

    // if no speaker, pass in empty string
    // if more than one speaker, pass "id1,id2"
    public boolean newEvent(int type, String startTime, String endTime, String speakerID,
                            String topic, String roomNumber, String capacity){
        return ems.newEvent(type, startTime, endTime, speakerID, topic, roomNumber, capacity, gw);
    }

    public boolean cancelEvent(String eventID){
        return ems.cancelEvent(eventID, gw);
    }

    public boolean changeEventCapacity(String eventId, String capacity) {
        return ems.changeEventCapacity(eventId, capacity, gw);
    }

    /**
     * change type of a event
     * @param eventId eventid of event
     * @return Return true if change correctly, false otherwise.
     */
    public boolean changeVipStatusOfEvent(int eventId, boolean type){
        return ems.changeVipStatusOfEvent(eventId, type, gw);
    }

    /**
     * Return all events that are currently scheduled.
     *
     * @return List of Strings of the events
     */
    // format [title, eventID, startTime, endTime, duration, room, VIPstatus]
    public List<List<String>> viewEvents(){
        return vs.viewEvents(gw);
    }

    /**
     * Return a list of events that the current logged in user has signed up for.
     * @return List of Strings of the events
     */
    // format [title, eventID, startTime, endTime, duration, room, VIPstatus]
    public List<List<String>> viewSignedUpEvents(){
        return vs.viewSignedUpEvents(gw);
    }

    /**
     * Return a list of events that the current logged in organizer has organized
     * @return List of Strings of the events
     */
    // format [title, eventID, startTime, endTime, duration, room, VIPstatus]
    public List<List<String>> viewOrganizedEvents(){
        return vs.viewOrganizedEvents(gw);
    }

    /**
     * Return a list of events that the current Speaker is going to speak for.
     * @return List of Strings of the events
     */
    // format [title, eventID, startTime, endTime, duration, room, VIPstatus]
    public List<List<String>> viewSpeakingEvents(){
        return vs.viewSpeakingEvents(gw);
    }

    // format [title, eventID, startTime, endTime, duration, room, VIPstatus]
    public List<List<String>> viewCanSignUpEvents(){
        /**
         * Return a list of events that the current logged in attendee can sign up for. VIP can sign all events, normal
         * user can only sign up normal events.
         * These events are events that the user do not need to wait in the waitlist.
         * @para userId is user id
         * @return List of List of Strings of the events
         */
        return vs.viewCanSignUpEvents(gw);
    }

    /**
     * Return a list of events' info that the current logged in user can waitlist for.
     * @return List of List of Strings of the events' info
     */
    // return format: [title, eventID, startTime, endTime, duration, room, VIPstatus, waitlistLength]
    public List<List<String>> viewCanWaitlistEvents(){
        return vs.viewCanWaitlistEvents(gw);
    }

    // when use this method, you need to restrict the type of user to be attendee type...
    // return format: [title, eventID, startTime, endTime, duration, room, VIPstatus, waitlistRank]
    public List<List<String>> viewMyWaitList(){
        return vs.viewMyWaitList(gw);
    }

    // format: [ID, username]
    public List<List<String>> viewAttendeesInSpeakingEvents(){
        return vs.viewAttendeesInSpeakingEvents(gw);
    }

    // format: [ID, username]
    public List<List<String>> viewAllAttendees(){
        return vs.viewAllAttendees(gw);
    }

    //format: [ID, username]
    public List<List<String>> viewAllSpeakers(){
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
