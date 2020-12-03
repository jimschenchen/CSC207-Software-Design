package entity.event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class NonSpeakerEvent extends Event{

    public NonSpeakerEvent(LocalDateTime startTime, LocalDateTime endTime,
                           int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
    }


    @Override
    public void setSpeaker() {
    }

    @Override
    public void setSpeaker(ArrayList<Integer> speakerList) {}

    @Override
    public void setSpeaker(int speakerId) {}
}
