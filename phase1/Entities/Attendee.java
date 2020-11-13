import java.util.ArrayList;
import java.util.List;
public class Attendee extends User{

    private ArrayList<Integer> signed_up_event;

    public Attendee(int uid,String password, String name) {
        super(uid, password, name);
        this.signed_up_event = new ArrayList<>();
    }

    public void signUpEvent (int eid){
            signed_up_event.add(eid);
    }

    public void cancelEvent (int eid){
            signed_up_event.remove(eid);
    }

    public List getEventList (){
        return signed_up_event;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "signed_up_event=" + signed_up_event +
                '}';
    }
}
