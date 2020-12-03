package entity.event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class MultiSpeakerEvent extends Event{
    private ArrayList<Integer> speakerList;

    public MultiSpeakerEvent(LocalDateTime startTime, LocalDateTime endTime,
                             int eventId, String title, int roomId, int capacity) {
        super(startTime, endTime, eventId, title, roomId, capacity);
    }

    public ArrayList<Integer> getSpeakerList() {
        return speakerList;
    }

    public void setSpeakerList(ArrayList<Integer> speakerList) {
        this.speakerList = speakerList;
    }

    public void addNewSpeaker(int speakerId) {
        speakerList.add(speakerId);
    }


    @Override
    public void setSpeaker(ArrayList<Integer> speakerList) {
        this.speakerList = speakerList;
    }

    @Override
    public void setSpeaker() {}

    @Override
    public void setSpeaker(int speakerId) {}
}
