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
import gateway.Gateway;

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
    public boolean canCreateEvent(int roomId, LocalDateTime start, LocalDateTime end, int capacity, Gateway g){
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
                           LocalDateTime end, String title, int roomId, int capacity, Gateway g){
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
                           LocalDateTime end, String title, int roomId, int capacity, Gateway g){
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
    public void setSpeaker(int speakerId, int eventId, Gateway g){

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
    public boolean canAddUserToEvent(int userId, int eventId, Gateway g) {
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
    public void addUserToEvent(int userId, int eventId, Gateway g){
        g.getEventById(eventId).addUserToEvent(userId);
    }

    /**
     * Judge whether a user can be removed by an event
     * @param userid the user id
     * @param eventId the event id
     * @param g the database
     * @return the boolean whether a user can be removed by an event
     */
    public boolean canRemoveUser(int userid, int eventId, Gateway g) {
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
    public void removeUser(int userId, int eventId, Gateway g) {
        g.getEventById(eventId).removeUserFromEvent(userId);

    }

    /**
     * A getter for the all signed up user of and event
     *
     * @param eventID the event id
     * @param g the database
     * @return all signed up user of and event
     */
    public List<Integer> getUserList(int eventID, Gateway g){
        Event event = g.getEventById(eventID);
        return event.getSignedUpUserList();
    }


    /**
     * A getter for ids of all events in the database
     * @param g the database
     * @return the list of ids of all events in the database
     */
    public List<Integer> getEventList(Gateway g){
        List<Integer> allEvents = new ArrayList<>();
        List<Event> events = g.getEventList();
        for (Event event : events){
            allEvents.add(event.getEventId());
        }
        return allEvents;
    }

    public String getStringOfEvent(int eventID, Gateway g){
        Event event = g.getEventById(eventID);
        return "The event " + event.getTitle() +
                " with ID " + event.getEventId() +
                " by " + g.getSpeakerById(eventID).getUserName() +
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
    public boolean isExistingEvent(int eventID, Gateway g){
        return g.getEventById(eventID) != null;
    }

    public boolean canCancelEvent(int eventID, Gateway gw) {
        return isExistingEvent(eventID, gw);
    }

    public void cancelEvent(int eventID, Gateway gw) {
        gw.deleteEvent(gw.getEventById(eventID));
    }

    public Duration getEventDuration(int eventId, Gateway g) {return g.getEventById(eventId).getDuration();}

    public boolean canChangeEventCapacity(int newCapacity,int eventId ,Gateway g) {
        if (newCapacity > g.getRoomById(g.getEventById(eventId).getRoomId()).getCapacity() |
                newCapacity < g.getEventById(eventId).getSignedUpUserList().size()) {
            return false;
        }
        return true;
    }

    public void changeEventCapacity(int newCapacity, int eventId, Gateway g) {
        g.getEventById(eventId).setCapacity(newCapacity);
    }

}

