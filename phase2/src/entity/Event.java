package entity;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * The Event class.
 */
public class Event implements java.io.Serializable{
    private LocalDateTime startTime;
    private int speakerId;
    private String title;
    private int roomId;
    private ArrayList<Integer> singnedUserId;
    private int eventId;

    /**
     * Constructor of an event object.
     *
     * @param start the start time of event
     * @param eventId the event id
     * @param speakerId the speaker id of this event
     * @param topic the title of the event
     * @param roomN the room number this event will be hold
     */
    public Event(LocalDateTime start, int eventId, int speakerId, String topic, int roomN){
        this.startTime = start;
        this.speakerId = speakerId;
        this.title = topic;
        this.roomId = roomN;
        this.singnedUserId = new ArrayList<>();
        this.eventId = eventId;
    }

    /**
     * Remove a signed up user from this event.
     *
     * @param userId the user id
     */
    public void removeUser(int userId){
        singnedUserId.remove(new Integer(userId));
    }

    /**
     * Add a user who want to sign up for this event.
     * @param userId the user id
     */
    public void addUser(int userId){
        singnedUserId.add(userId);
    }

    /**
     * a getter for the start time of this event.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * a getter for the speaker id of this event.
     *
     * @return the speaker id
     */
    public int getSpeakerId() {
        return speakerId;
    }

    /**
     * A getter for the title of this event.
     *
     * @return the title of this event
     */
    public String getTitle() {
        return title;
    }

    /**
     * A getter for the room id of this event.
     *
     * @return the room id this event will take place
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * A getter for the event id
     *
     * @return the event id of this event
     */
    public int getEventId() {
        return eventId;
    }

    public ArrayList<Integer> getSingnedUserId() {
        return singnedUserId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "speaker id=" + speakerId +
                ", event id=" + eventId +
                '}';
    }
}
