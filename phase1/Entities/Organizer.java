import java.util.ArrayList;
import java.util.List;

public class Organizer extends Attendee{

    private ArrayList<Integer> created_event_list;


    public Organizer(int uid, String password, String name) {
        super(uid, password, name);
        this.created_event_list = new ArrayList<>();
    }

    public void AddCreatedEvent (int eid) {
        if (!created_event_list.contains(eid)) {
            created_event_list.add(eid);
        }
    }

    public ArrayList<Integer> getCreatedEventList() {
        return created_event_list;
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "created_event_list=" + created_event_list +
                '}';
    }
}
