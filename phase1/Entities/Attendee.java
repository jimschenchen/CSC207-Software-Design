import java.util.ArrayList;
import java.util.List;
public class Attendee extends User{
    private List<Event> event_list;
    public Attendee(String passw, String name) {
        super(passw, name);
        this.event_list = new ArrayList<>();
    }

    public void add_event (Event event){
        this.event_list.add(event);
    }

    public void cancel_event (Event event){
        this.event_list.remove(event);
    }

    public List get_partiipared_event_schedule(){
        List<String> event_title = new ArrayList<>();
        for (Event e : this.event_list){
            event_title.add(e.getTitle() + " start at: " + String.valueOf(e.getStart_time()));
        }
        return event_title;
    }
}
