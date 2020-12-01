import java.time.LocalDateTime;
import java.util.ArrayList;

public class PanelDiscussion extends MultiSpeakerEvent{
    public PanelDiscussion(ArrayList<Integer> speakerList, LocalDateTime startTime, LocalDateTime endTime,
                           int eventId, String title, int roomId, int capacity) {
        super(speakerList, startTime, endTime, eventId, title, roomId, capacity);
    }
}
