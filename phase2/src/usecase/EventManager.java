package usecase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;
import entity.*;
import entity.event.*;
import entity.eventFactory.FactoryProducer;
import gateway.GatewayFacade;

/**
 * The Event Manager class
 */
public class EventManager {


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Judge whether the new event can be created
     *
     * @param roomId the room id this new event will take place
     * @param start the start time of this new event
     * @param g the database
     * @return the boolean show whether the new event can be created
     */
    public boolean canCreateEvent(int roomId, LocalDateTime start, LocalDateTime end, int capacity, GatewayFacade g){
        List<Event> allEvent = g.getEventList();
        for (Event event : allEvent) {
            if (roomId == event.getRoomId() &&
                    (event.getStartTime().isBefore(end) & !(event.getEndTime().isBefore(start))) &
                            capacity > g.getRoomById(roomId).getCapacity()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create a new event
     * @param start the start time
     * @param speakerId the speaker id of this event
     * @param title the title of this event
     * @param roomId the room id where this event will take place
     * @param g the database
     * @return the new event id
     */
    public int createEvent(int type1, int type2, @Nullable int speakerId, LocalDateTime start,
                           LocalDateTime end, String title, int roomId, int capacity, GatewayFacade g){
        Event nEvent = FactoryProducer.getFactory(type1).getEvent(type2, start, end,
                g.getNextEventId(), title, roomId, capacity);
        g.addEvent(nEvent);
        if (type1 == 0) {
            g.getNonSpeakerEventById(nEvent.getEventId()).setSpeaker(speakerId);
        }
        else {
            g.getOneSpeakerEventById(nEvent.getEventId()).setSpeaker(speakerId);
        }
        return nEvent.getEventId();
    }

    public int createEvent(int type1, int type2, ArrayList<Integer> speakerList, LocalDateTime start,
                           LocalDateTime end, String title, int roomId, int capacity, GatewayFacade g){
        Event nEvent = FactoryProducer.getFactory(type1).getEvent(type2, start, end,
                g.getNextEventId(), title, roomId, capacity);
        g.addEvent(nEvent);
        g.getMultiSpeakerEventById(nEvent.getEventId()).setSpeaker(speakerList);
        return nEvent.getEventId();
    }

    /**
     * Set a new speaker to the exist event
     * @param speakerId the new speaker id
     * @param eventId the event id
     * @param g the database
     */
    public void setSpeaker(int speakerId, int eventId, GatewayFacade g){

        g.getEventById(eventId).setSpeaker(speakerId);
    }

    /**
     * Judge whether a user can sign up to an event
     *
     * @param userId the userid
     * @param eventId the event id
     * @param g the database
     * @return the boolean shows whether a user can sign up to an event
     */
    public boolean canAddUserToEvent(int userId, int eventId, GatewayFacade g) {
        Event e = g.getEventById(eventId);
        if (!isExistingEvent(eventId, g)) {
            return false;
        }
        else if (! (g.getUserById(userId) instanceof VipUser) & e.isVipEvent()) {
            return false;
        }
        else {
            if (e.getSignedUpUserList().contains(userId)
                    | e.getCapacity() >= e.getSignedUpUserList().size()) {
                return false;
            }
            return true;
        }
    }

    /**
     * Add user to an event
     * @param userId the user id
     * @param eventId the event id
     * @param g the database
     */
    public void addUserToEvent(int userId, int eventId, GatewayFacade g){
        g.getEventById(eventId).addUserToEvent(userId);
    }

    /**
     * Judge whether a user can be removed by an event
     * @param userid the user id
     * @param eventId the event id
     * @param g the database
     * @return the boolean whether a user can be removed by an event
     */
    public boolean canRemoveUser(int userid, int eventId, GatewayFacade g) {
        if (isExistingEvent(eventId, g)) {
            return g.getEventById(eventId).getSignedUpUserList().contains(userid);
        }
        return false;
    }

    /**
     * Remove the user from an event
     * @param userId the user id
     * @param eventId the event id
     * @param g the database
     */
    public void removeUser(int userId, int eventId, GatewayFacade g) {
        g.getEventById(eventId).removeUserFromEvent(userId);

    }

    /**
     * A getter for the all signed up user of and event
     *
     * @param eventID the event id
     * @param g the database
     * @return all signed up user of and event
     */
    public List<Integer> getUserList(int eventID, GatewayFacade g){
        Event event = g.getEventById(eventID);
        return event.getSignedUpUserList();
    }


    /**
     * A getter for ids of all events in the database
     * @param g the database
     * @return the list of ids of all events in the database
     */
    public List<Integer> getEventList(GatewayFacade g){
        List<Integer> allEvents = new ArrayList<>();
        List<Event> events = g.getEventList();
        for (Event event : events){
            allEvents.add(event.getEventId());
        }
        return allEvents;
    }

    public List<Integer> getNormalEventList(GatewayFacade g){
        List<Integer> normalEvents = new ArrayList<>();
        List<Event> events = g.getEventList();
        for (Event event : events){
            if(event.isVipEvent() == false) {
                normalEvents.add(event.getEventId());
            }
        }
        return normalEvents;
    }

    public String getStringOfEvent(int eventID, GatewayFacade g){
        Event event = g.getEventById(eventID);
        return "The event " + event.getTitle() +
                " with ID " + event.getEventId() +
                " starts at " + event.getStartTime().format(formatter) +
                " ends at " + event.getEndTime().format(formatter) +
                " takes place in " + g.getRoomById(event.getRoomId()).getRoomNum();
    }

    /**
     * A getter for the event start time format
     *
     * @return the event start time format
     */
    public DateTimeFormatter getTimeFormatter(){
        return this.formatter;
    }

    /**
     * Judge whether the event is in the database
     * @param eventID event id
     * @param g the database
     * @return the boolean shows whether the event is in the database
     */
    public boolean isExistingEvent(int eventID, GatewayFacade g){
        return g.getEventById(eventID) != null;
    }

    public boolean canCancelEvent(int eventID, GatewayFacade gw) {
        return isExistingEvent(eventID, gw);
    }

    public void cancelEvent(int eventID, GatewayFacade gw) {
        gw.deleteEvent(gw.getEventById(eventID));
    }

    public Duration getEventDuration(int eventId, GatewayFacade g) {return g.getEventById(eventId).getDuration();}

    public boolean canChangeEventCapacity(int newCapacity,int eventId ,GatewayFacade g) {
        if (newCapacity > g.getRoomById(g.getEventById(eventId).getRoomId()).getCapacity() |
                newCapacity < g.getEventById(eventId).getSignedUpUserList().size()) {
            return false;
        }
        return true;
    }

    public void changeEventCapacity(int newCapacity, int eventId, GatewayFacade g) {
        g.getEventById(eventId).setCapacity(newCapacity);
    }


    public boolean changeVipStatusOfEvent(int eventId, Boolean type, GatewayFacade g){
        /**
         * change type of a event
         * @param eventId eventid of event
         * @param type type of event
         * @return Return true if change correctly, false otherwise.
         */
        g.getEventById(eventId).setVipEvent(type);
        return true;
    }

    public Boolean getVipStatusOfEvent(int eventId, GatewayFacade g){
        /**
         * return the event type, true means event is VIP, false means event is not VIP
         * @param eventID event id
         * @return the type of event
         */
        return g.getEventById(eventId).isVipEvent();
    }
}

