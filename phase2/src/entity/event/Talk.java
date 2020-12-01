package entity.event;

import java.time.LocalDateTime;

public class Talk extends OneSpeakerEvent{
    public Talk(int speakerId, LocalDateTime startTime, LocalDateTime endTime,
                int eventId, String title, int roomId, int capacity) {
        super(speakerId, startTime, endTime, eventId, title, roomId, capacity);
    }
}
