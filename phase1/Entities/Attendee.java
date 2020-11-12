import java.util.ArrayList;
import java.util.List;
public class Attendee extends User{

    private ArrayList<Integer> signed_up_event;

    public Attendee(int uid,String password, String name) {
        super(uid, password, name);
        this.signed_up_event = new ArrayList<>();
    }

    public boolean signUpEvent (int eid){
        if (signed_up_event.contains(eid)) {
            return false;
        }
        else {
            signed_up_event.add(eid);
            return true;
        }

    }

    public boolean cancelEvent (int eid){
        if (signed_up_event.contains(eid)) {
            signed_up_event.remove(eid);
            return true;
        }
        else {
            return false;
        }
    }

    public List getEventList (){
        return signed_up_event;
    }

}
