import java.util.List;
/*
 * CSC207.Store All the Data
 */
public class DataBase {
    /**
     * Static storage
     */
//    public static DataBase data;

    /**
     * Attributes
     * NOTE: Use @SerializeName("total_number") to change the serilization rule
     */
    private int userNumber;
    private int eventNumber;
    private List<User> userList;
    private List<Event> eventList;

    /**
     *
     */


    /**
     * Getter & Setter
     */
    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
