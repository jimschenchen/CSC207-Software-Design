package entity.event;

//import com.sun.istack.internal.Nullable;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class NonSpeakerEvent extends Event{

    public NonSpeakerEvent(LocalDateTime startTime, LocalDateTime endTime,
                           int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
    }

    public void setSpeaker(@Nullable int speakerId) {}

    public void getSpeakerId() {};
}
