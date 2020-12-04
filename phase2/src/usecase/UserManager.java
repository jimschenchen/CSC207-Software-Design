package usecase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import entity.*;
import entity.event.*;
import gateway.Gateway;
import gateway.GatewayFacade;


public class UserManager {

    public boolean canCreateUser(String userName, GatewayFacade gw){
        /**
         * @Description: checks if username is unique
         */
        if (userName.trim().length() > 0 && gw.getUserByUserName(userName) == null){
            return true;
        }
        return false;
    }

    public void createSpeaker(String password, String name, GatewayFacade g){
        /**
         * @Description: create a Speaker account
         */
        Speaker s = new Speaker(g.getNextUserId(), password, name);
        g.addUser(s);
    }


    public void createAttendee(String password, String name ,GatewayFacade g) {
        /**
         * @Description: create a Attendee account
         */
        Attendee a = new Attendee(g.getNextUserId(), password, name);
        g.addUser(a);
    }

    public void createVIP(String password, String name ,GatewayFacade g){
        /**
         * @Description: create a VIP account
         */
        VipUser vip = new VipUser(g.getNextEventId(), password, name);
        g.addUser(vip);
    }

    public boolean canAddEventToSpeaker(int eventID, int speakerId, GatewayFacade g){
        /**
         * @Description: judge whether a Event is conflict with Events which a Speaker participate
         */
        Speaker s = g.getSpeakerById(speakerId);
        Event event = g.getEventById(eventID);
        if (s == null || event == null || event instanceof NonSpeakerEvent) {
            return false;
        }
        else return !isSpeakerBusy(speakerId, event.getStartTime(), event.getEndTime(), g);
    }

    public void addEventToSpeaker(int eventId, int speakerId, GatewayFacade g){
        /**
         * @Description: add a Event to a Speaker
         */
        Event event = g.getEventById(eventId);
        if (event instanceof OneSpeakerEvent){
            removeOneSpeakerEventFromSpeaker(eventId, g);
        }
        g.getSpeakerById(speakerId).addGivingEvent(eventId);

    }

    private void removeOneSpeakerEventFromSpeaker(int eventId, GatewayFacade g) {
        /**
         * @Description: remove a Event to a Speaker
         */
        OneSpeakerEvent e =  g.getOneSpeakerEventById(eventId);
        g.getSpeakerById(e.getSpeakerId()).removeGivingEvent(eventId);
    }

    public boolean canSignUpForEvent(int eventId, int userId, GatewayFacade g) {
        /**
         * @Description: judge whether a User is eligible to sign up an Event
         */
        Event e = g.getEventById(eventId);
        if (!(e == null | isExistingSpeaker(userId, g)
                || (!(g.getUserById(userId) instanceof VipUser) & e.isVipEvent()))) {
            Attendee a = (Attendee) g.getUserById(userId);

            for (int i = 0; i < a.getSignedUpEventList().size(); i++) {
                if (g.getEventById(a.getSignedUpEventList().get(i)).getStartTime().isBefore(e.getEndTime())
                        & g.getEventById(a.getSignedUpEventList().get(i)).getEndTime().isAfter(e.getStartTime())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void addEventToUser(int eventId, int userId, GatewayFacade g){
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

    public void cancelEventFromUser(int eventId, int userId, GatewayFacade g) {
        /**
         * @Description: cancel an Event for Attendee or Organizer
         */
        ((Attendee) g.getUserById(userId)).cancelEvent(eventId);
    }

    public void transferWaitingEventToSignedUp(int eventId, int userId, GatewayFacade g) {
        ((Attendee) g.getUserById(userId)).signUpEvent(eventId);
        ((Attendee) g.getUserById(userId)).removeWaitingEvent(eventId);
    }

    public void addEventToMyWaitList(int eventId, int userId, GatewayFacade g) {
        ((Attendee) g.getUserById(userId)).addWaitingEvent(eventId);
    }

    public void cancelEventFromMyWaitList(int eventId, int userId, GatewayFacade g) {
        ((Attendee) g.getUserById(userId)).cancelEvent(eventId);
    }

    /**
     * Add event to the list of organized events of an organizer
     * @param eventID Event's ID
     * @param organizerID Organizer's ID
     * @param g Database
     */
    public void addEventToOrganizedList(int eventID, int organizerID, GatewayFacade g){
        Organizer organizer = g.getOrganizerById(organizerID);
        organizer.AddCreatedEvent(eventID);
    }

    public void setPassword(int userId,String password, GatewayFacade g) {
        /**
         * @Description: set password for an User
         */
        g.getUserById(userId).setPassword(password);
    }

    public String getUserName(int userId, GatewayFacade g) {
        /**
         * @Description: get User name by userid
         */
        return g.getUserById(userId).getUserName();
    }

    public ArrayList<Integer> getUserSignedUpEvent(int userId, GatewayFacade g){
        /**
         * @Description: get Organizer or Attendee Event list
         */
            Attendee a = (Attendee) g.getUserById(userId);
            return a.getSignedUpEventList();
    }

    public ArrayList<Integer> getUserWaitList(int userId, GatewayFacade g) {
        /**
         *
         * @param userId event id
         * @param g the database
         * @Description get the waitlist of the user.
         */
            Attendee a = (Attendee) g.getUserById(userId);
            return a.getMyWaitList();
    }

    public List<Integer> getOrganizedEventList(int organizerID, GatewayFacade g){
        /**
         *
         * @param userId event id
         * @param g the database
         * @Description get the event list of the organizer.
         */
        return g.getOrganizerById(organizerID).getCreatedEventList();
    }

    public List<Integer> getSpeakerGivingEventList(int speakerID, GatewayFacade g){
        /**
         * @Description: get Speaker Event list
         */
        return g.getSpeakerById(speakerID).get_GivingEventList();
    }

    public String getUserPassword(String username, GatewayFacade g){
        /**
         * @Description: get User password by username
         */
        return g.getUserByUserName(username).getPassword();
    }

    public int getUserID(String username, GatewayFacade g){
        /**
         * @Description: get User id by username
         */
        return g.getUserByUserName(username).getUserId();
    }

    public int getUserCategory(int id, GatewayFacade g){
        /**
         * @Description: judge the User category, 0 means speaker, 1 means organizer, 2 means attendee, 3 means VIP
         */
        if (isExistingSpeaker(id, g)){
            // return 0 when id is a speaker
            return 0;
        }
        else if (isExistingOrganizer(id, g){
            return 1;
        }
        if (isExistingAttendee(id, g)) {
            return 2;
        }
        else{
            return 3;
        }
    }


    public List<Integer> getListOfUsers(int userType, GatewayFacade g){
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

    public int getUserRankInWaitList(int userId, int eventId, GatewayFacade g) {
        return g.getEventById(eventId).getWaitList().indexOf(userId);
    }

    public String getUserString(int userID, GatewayFacade g){
        /**
         * @Description: get User name and id
         */
        User user = g.getUserById(userID);
        return user.getUserName() + " (" + user.getUserId() + ")";
    }

    public boolean isSpeakerBusy(int speakerId, LocalDateTime start, LocalDateTime end, GatewayFacade g) {
        /**
         * @Description: judge whether a speaker is speaking
         */
        for (int eid :g.getSpeakerById(speakerId).get_GivingEventList()) {
            if (g.getEventById(eid).getEndTime().isBefore(start) | g.getEventById(eid).getStartTime().isAfter(end)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpeakerBusy(ArrayList<Integer> speakerId, LocalDateTime start, LocalDateTime end, GatewayFacade g) {
        /**
         *
         * @Description check if a speaker is busy or not.
         */
        for (int sid : speakerId) {
            if (isSpeakerBusy(sid, start, end, g)) {
                return true;
            };
        }
        return false;
    }

    public boolean isExistingSpeaker(int userID, GatewayFacade g){
        /**
         * @Description: judge a speaker is exist
         */
        return g.getSpeakerById(userID) != null;
    }

    public boolean isExistingSpeaker(ArrayList<Integer> speakerList, GatewayFacade g) {
        /**
         *
         * @Description check if a speaker exists
         */
        for (int i : speakerList) {
            if (g.getSpeakerById(i) == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isExistingUser(String username, GatewayFacade g){
        /**
         * @Description: judge a User is exist
         */
        return g.getUserByUserName(username) != null;
    }

    public boolean isExistingAttendee(int userId, GatewayFacade g){
        /**
         * @Description: judge an Attendee is exist
         */
        return g.getAttendeeById(userId) != null;
    }

    public boolean isExistingOrganizer(int userId, GatewayFacade g){
        /**
         * @Description: judge an organizer is exist
         */
        return g.getOrganizerById(userId) != null;
    }

//    public boolean isExistingVipUser(int userId, GatewayFacade g) {
//        return g.getVipUserById(userId) != null;
//    }

    public boolean canCancelEvent(int userID, int eventID, GatewayFacade gw) {
        /**
         *
         * @Description check if an event can be canceled
         */
        // only the organizer who organized the event can cancel the event
        try{
            Organizer user = gw.getOrganizerById(userID);
            return user.getCreatedEventList().contains(eventID);
            }
        catch(NullPointerException npe){
            return false; // return false when userID is not pointing to organizer
        }
        // any organizer can cancel an event
        // return isExistingOrganizer(userID, gw);
    }

    public void cancelEvent(int eventID, GatewayFacade gw) {
        /**
         *
         * @Description cancel an event.
         */
        Event event = gw.getEventById(eventID);
        List<Integer> userList = event.getSignedUpUserList();
        for (Integer userID : userList){
            Attendee user = (Attendee) gw.getUserById(userID);
            user.cancelEvent(eventID);
        }
    }
}