import java.time.LocalDateTime;
import java.util.ArrayList;

public class MultiSpeakerEventFactory extends AbstractEventFactory{
    @Override
    public Event getNonSpeakerEvent(int type, LocalDateTime startTime,
                                    LocalDateTime endTime, int eventId, String title, int roomId, int capacity) {
        return null;
    }

    @Override
    public Event getOneSpeakerEvent(int type, int speakerId, LocalDateTime startTime,
                                    LocalDateTime endTime, int eventId, String title, int roomId, int capacity) {
        return null;
    }

    @Override
    public Event getMultiSpeakerEvent(int type, ArrayList<Integer> speakerList,
                                      LocalDateTime startTime, LocalDateTime endTime,
                                      int eventId, String title, int roomId, int capacity) {
        if (type == 0) {
            return new PanelDiscussion(speakerList, startTime, endTime, eventId, title, roomId, capacity);
        }
        return null;
    }
}
