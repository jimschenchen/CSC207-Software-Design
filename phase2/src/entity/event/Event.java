package entity.event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class  Event {
    private int eventId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private String title;
    private int roomId;
    private int capacity;
    private boolean isVipEvent = false;
    private ArrayList<Integer> signedUserList = new ArrayList<>();
    private ArrayList<Integer> waitList = new ArrayList<>();


    public Event(LocalDateTime startTime, LocalDateTime endTime, int eventId, String title, int roomId, int capacity){
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventId = eventId;
        this.title = title;
        this.roomId = roomId;
        this.capacity = capacity;
        this.duration.between(startTime,endTime);
    }

    public int getEventId() {
        return eventId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isVipEvent() {
        return isVipEvent;
    }

    public void setVipEvent(boolean vipEvent) {
        isVipEvent = vipEvent;
    }

    public ArrayList<Integer> getSignedUpUserList() {
        return signedUserList;
    }

    public ArrayList<Integer> getWaitList() {
        return waitList;
    }

    public void removeUser(int userId) {
        signedUserList.remove(userId);
    };

    public abstract void setSpeaker();

    public abstract void setSpeaker(ArrayList<Integer> speakerList);

    public abstract void setSpeaker(int speakerId);
}
