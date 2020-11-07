import java.util.List;
import Store.*;
/*
 * CSC207.Store All the Data
 */
public class DataBase {
    /**
     * Attributes
     * NOTE: Use @SerializeName("total_number") to change the serilization rule
     */
    private int userNumber;
    private int eventNumber;
    private List<UserStore> userList;
    private List<UserStore> eventList;

    /**
     * Sub Bean
     */

    public class EventBean{
        private int eid;
        private String name ;

        public EventBean(int eid, String name) {
            this.eid = eid;
            this.name = name;
        }
    }

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

    public List<UserStore> getUserList() {
        return userList;
    }

    public void setUserList(List<UserStore> userList) {
        this.userList = userList;
    }

    public List<UserStore> getEventList() {
        return eventList;
    }

    public void setEventList(List<UserStore> eventList) {
        this.eventList = eventList;
    }
}
