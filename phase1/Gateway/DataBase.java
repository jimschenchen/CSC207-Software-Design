import java.util.ArrayList;
import java.util.List;

/*
 * CSC207.Store All the Data
 */
public class DataBase {
    /**
     * Attributes
     * NOTE: Use @SerializeName("total_number") to change the serilization rule
     */
    private int nextUserId = 0;
    private int nextEventId = 0;
    private int nextRoomId = 0;
    private int nextMessageId = 0;
    private List<User> userList = new ArrayList<User>();
    private List<Event> eventList = new ArrayList<Event>();
    private List<Room> roomList = new ArrayList<Room>();
    private List<Message> messageList = new ArrayList<Message>();


    /**
     * === Getter & Setter ===
     */

    /** Return the next user id and self increase by 1 */
    public int getNextUserId() {
        return nextUserId++;
    }

    /** Return the next event id and self increase by 1 */
    public int getNextEventId() {
        return nextEventId++;
    }

    /** Return the next room id and self increase by 1 */
    public int getNextRoomId() {
        return nextRoomId++;
    }

    /** Return the next message id and self increase by 1 */
    public int getNextMessageId() {
        return nextMessageId++;
    }

    /**
    * @Description:
    * @Param: [id]
    * @return: User
    * @Author:
    * @Date: 2020-11-11
    */
    public User getUserById(int id) {
        ArrayList<User> userList = (ArrayList<User>) getUserList();
        for (User u : userList) {
            if (u.getUser_id() == id) {
                return u;
            }
        }
        return null;
    }
    /**
    * @Description:
    * @Param: []
    * @return: java.util.List<User>
    * @Author:
    * @Date: 2020-11-11
    */
    public List<User> getUserList() {
        return userList;
    }

    /**
    * @Description:
    * @Param: []
    * @return: java.util.List<Event>
    * @Author:
    * @Date: 2020-11-11
    */
    public List<Event> getEventList() {
        return eventList;
    }
    /**
    * @Description:
    * @Param: [id]
    * @return: Event
    * @Author:
    * @Date: 2020-11-11
    */
    public Event getEventById(int id) {
        ArrayList<Event> eventList = (ArrayList<Event>) getEventList();
        for (Event e : eventList) {
            if (e.getEvent_id() == id) {
                return e;
            }
        }
        return null;
    }

    /**
    * @Description:
    * @Param: []
    * @return: java.util.List<Room>
    * @Author:
    * @Date: 2020-11-11
    */
    public List<Room> getRoomList() {
        return roomList;
    }
    /**
    * @Description:
    * @Param: [id]
    * @return: Room
    * @Author:
    * @Date: 2020-11-11
    */
    public Room getRoomById(int id) {
        ArrayList<Room> roomList = (ArrayList<Room>) getRoomList();
        for (Room r : roomList) {
            if (r.getRid() == id) {
                return r;
            }
        }
        return null;
    }

    /**
    * @Description:
    * @Param: []
    * @return: java.util.List<Message>
    * @Date: 2020-11-11
    */
    public List<Message> getMessageList() {
        return messageList;
    }
    /**
    * @Description: Return the List of messages related to <userId>; Cannot add message
    * @Param: [userId]
    * @return: java.util.List<Message>
    * @Date: 2020-11-11
    */
    public List<Message> getMessageListByUserId(int userId) {
        ArrayList<Message> messageList = (ArrayList<Message>) getMessageList();
        List<Message> ret = new ArrayList<Message>();
        for (Message m : messageList) {
            if (m.getReceiverId() == userId || m.getReceiverId() == userId) {
                ret.add(m);
            }
        }
        return ret;
    }
    public void addMessage(Message message) {
        this.messageList.add(message);
    }

}
