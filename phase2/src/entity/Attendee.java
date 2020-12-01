import java.util.ArrayList;
import java.util.List;

/**
 * The class Attendee, extends from User.
 */
public class Attendee extends User implements java.io.Serializable{

    private ArrayList<Integer> signedUpEvent;

    /**
     * Constructor of a Attendee object.
     *
     * @param uid the user id
     * @param password the user password
     * @param name the username
     */
    public Attendee(int uid,String password, String name) {
        super(uid, password, name);
        this.signedUpEvent = new ArrayList<>();
    }

    /**
     * Sign up an event.
     *
     * @param eid the event id
     */
    public void signUpEvent (int eid){
        signedUpEvent.add(eid);
    }

    /**
     * Cancel an event.
     *
     * @param eid event id
     */
    public void cancelEvent (int eid){
            signedUpEvent.remove(eid);
    }

    /**
     * A getter for list of signed up events' ids
     * @return the list of signed up events' ids
     */
    public ArrayList<Integer> getEventList (){
        return signedUpEvent;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "uid=" + super.getUserId() +
                ", username='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", signed up event=" + signedUpEvent +
                '}';
    }
}