import java.util.ArrayList;
import java.util.List;

public class Speaker extends User implements java.io.Serializable{

    private ArrayList<Integer> giving_event_list;


    public Speaker(int uid, String password, String name) {
        super(uid, password, name);
        this.giving_event_list = new ArrayList<>();
    }

    public void addGivingEvent (int eid){
        giving_event_list.add(eid);
    }

    public ArrayList<Integer> get_GivingEventList(){
        return giving_event_list;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "giving_event_list=" + giving_event_list +
                '}';
    }
}
