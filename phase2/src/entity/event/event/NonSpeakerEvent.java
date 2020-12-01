package event.event;

import java.time.LocalDateTime;

public abstract class NonSpeakerEvent extends Event{

    public NonSpeakerEvent(LocalDateTime startTime, LocalDateTime endTime,
                           int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
    }
}
