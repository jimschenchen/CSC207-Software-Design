package entity.event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class NonSpeakerEvent extends Event implements IEvent{

    public NonSpeakerEvent(LocalDateTime startTime, LocalDateTime endTime,
                           int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
    }

    public void getSpeakerId() {};
}
