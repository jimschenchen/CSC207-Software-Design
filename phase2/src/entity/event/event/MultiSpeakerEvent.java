import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class MultiSpeakerEvent extends Event{
    private ArrayList<Integer> speakerList;

    public MultiSpeakerEvent(ArrayList<Integer> speakerList, LocalDateTime startTime, LocalDateTime endTime,
                             int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
        this.speakerList = speakerList;
    }

    public ArrayList<Integer> getSpeakerList() {
        return speakerList;
    }

    public void addNewSpeaker(int speakerId) {
        speakerList.add(speakerId);
    }
}
