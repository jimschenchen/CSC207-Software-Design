package entity.event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class OneSpeakerEvent extends Event{
    private int speakerId;
    public OneSpeakerEvent(LocalDateTime startTime, LocalDateTime endTime,
                           int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public void setSpeaker(int speakerId) {
        this.speakerId = speakerId;
    }
}
