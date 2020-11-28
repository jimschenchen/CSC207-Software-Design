import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.jvm.hotspot.utilities.Assert;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: group_0173
 * @description: This Gateway connect to remote Redis Database and store all the entities in serialized json format.
 *      Since Nosql DB does not perform well on User storage, the runtime may be longer than expectation. It is better
 *      to change to sql DB to have a better performance.
 * @author:
 * @create: 2020-11-27 10:55
 **/
public class Gateway {

    private JedisPool jedisPool;
    static final String DATABASE_URL = "207.246.94.177";
    static final int DATABASE_PORT = 6379;
    static final String DATABASE_PASSWORD = "207207";

    /** NAME IN DB*/
    static final String NEXT_USER_ID = "next_user_id";
    static final String NEXT_EVENT_ID = "next_event_id";
    static final String NEXT_ROOM_ID = "next_room_id";
    static final String USER_HASH = "user_hash";
    static final String EVENT_HASH = "event_hash";
    static final String ROOM_HASH = "room_hash";
    static final String MESSAGE_LIST = "message_list";

    public void init() {
        shutDownHook();
        initJedisPool();
        ping();
    }
    public void initJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(poolConfig, DATABASE_URL, DATABASE_PORT, 10000, DATABASE_PASSWORD);
        System.out.println("Gateway: Jedis Pool has been Established");
    }
    public void close() {
        if(jedisPool != null){
            jedisPool.destroy();
            System.out.println("Gateway: Jedis Pool is Closed");
        }
    }
    private void shutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> close()));
        System.out.println("Gateway: ShutdownHook has been Added");
    }

    public Jedis getJedis() {
        try{
            return jedisPool.getResource();
        }catch(Exception e) {
            System.out.println("Gateway: getJedis Fails\n");
            e.printStackTrace();
            return null;
        }
    }
    public void closeJedis(Jedis jedis) {
        if(null != jedis) jedis.close();
    }
    public void ping() {
        try(Jedis jedis = jedisPool.getResource()){
            System.out.println("Gateway: Jedis " + (jedis.ping().equals("PONG") ? "is running" : "is stopped"));
        }
    }

    /*
     * ===== Gson =====
     */
    public Gson gson () {
        return new GsonBuilder().serializeNulls().create();
    }
    public Gson gson (Type type, Object typeAdapter) {
        return new GsonBuilder().registerTypeAdapter(type, typeAdapter).serializeNulls().create();
    }
    // ===== Gson =====


    // String Type
    /** Helper Func for get <key> id and increase it by 1 */
    private int getNextId (String key) {
        Jedis jedis = getJedis();
        int ret = 0;
        String response = jedis.get(key);
        if (response == null) {
            jedis.set(key, "1");
        } else {
            ret = Integer.parseInt(response);
            jedis.incr(key);
        }
        closeJedis(jedis);
        return ret;
    }

    /** Return the next user id and self increase by 1 */
    public int getNextUserId() {
        return getNextId(NEXT_USER_ID);
    }

    /** Return the next event id and self increase by 1 */
    public int getNextEventId() {
        return getNextId(NEXT_EVENT_ID);
    }

    /** Return the next room id and self increase by 1 */
    public int getNextRoomId() {
        return getNextId(NEXT_ROOM_ID);
    }


    // Helper Function
    /** Helper Func, adding (id, value) to the <key>map */
    private void addToHash (String key, int id, String value) {
        Jedis jedis = getJedis();
        String type = jedis.type(key);
        if (type.equals("hash")) {
            jedis.hset(key, String.valueOf(id), value);
        } else {
            jedis.del(key);
            Map<String,String> map = new HashMap<>();
            map.put(String.valueOf(id), value);
            jedis.hmset(key,map);
        }
        closeJedis(jedis);
    }
    private void updateToHash(String key, int id, String value) {
        addToHash(key, id, value);
    }
    private Map<String, String> getAllFromHash(String key) {
        Jedis jedis = getJedis();
        Map<String, String> map = jedis.hgetAll(key);
        closeJedis(jedis);
        return map;
    }
    private String getByIdFromHash (String key, int id) {
        Jedis jedis = getJedis();
        String value = jedis.hget(key, String.valueOf(id));
        closeJedis(jedis);
        return value;
    }
    private void addToList (String key, String value) {
        Jedis jedis = getJedis();
        String type = jedis.type(key);
        if (!type.equals("list")) {
            jedis.del(key);
        }
        jedis.lpush(key, value);
        closeJedis(jedis);
    }
    private List<String> getAllFromList (String key) {
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(key,0, -1);
        closeJedis(jedis);
        return list;
    }

    // ===== User: Hash=====
    /**
    * @Description: Add user to DB. It will automatically substitute the user in DB with the same uid
    * @Param: [user]
    * @return: void
    * @Author: 
    * @Date: 2020-11-28
    */
    public void addUser(User user) {
        UserBean userBean = new UserBean(user);
        String serData = gson(User.class, new UserAdapter()).toJson(userBean);
        addToHash(USER_HASH, user.getUser_id(), serData);
    }
    public void updateUser(User user) {
        UserBean userBean = new UserBean(user);
        String serData = gson(User.class, new UserAdapter()).toJson(userBean);
        updateToHash(USER_HASH, user.getUser_id(), serData);
    }
    /**
    * @Description: Get user by given id.
    * @Param: [id]
    * @return: User
    * @Author: 
    * @Date: 2020-11-28
    */
    public User getUserById(int id) {
        String serData = getByIdFromHash(USER_HASH, id);
        UserBean userBean = gson(User.class, new UserAdapter()).fromJson(serData, UserBean.class);
        return userBean.convertToUser();
    }
    /**
    * @Description: Get the Whole User List. *This is method may lag the performance.
    * @Param: []
    * @return: java.util.List<User>
    * @Author: 
    * @Date: 2020-11-28
    */
    public List<User> getUserList() {
        List<String> dateList = new ArrayList<>(getAllFromHash(USER_HASH).values());
        List<User> userList = new ArrayList<>();
        Gson gson = gson(User.class, new UserAdapter());
        for (String serData : dateList) {
            try {
                UserBean userBean = gson.fromJson(serData, UserBean.class);
                userList.add(userBean.convertToUser());
            } catch(Exception e) {
            }
        }
        return userList;
    }
    /**
    * @Description: Get User by given username. *This is method may lag the performance. Then use `getUserById` instead.
    * @Param: [username]
    * @return: User
    * @Author: 
    * @Date: 2020-11-28
    */
    public User getUserByUserName(String username) {
        ArrayList<User> userList = (ArrayList<User>) getUserList();
        for (User u : userList) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * @Description: Return Attendee by given <id>, if the user does not exist or is not Attendee, return null
     * @Param: [id]
     * @return: Attendee
     * @Author:
     * @Date: 2020-11-12
     */
    public Attendee getAttendeeById(int id) {
        User user = this.getUserById(id);
        if (user.getClass().equals(Attendee.class)) {
            return (Attendee)user;
        }
        return null;
    }

    /**
     * @Description: get attendee by given username
     * @Param: [username]
     * @return: Attendee
     * @Author:
     * @Date: 2020-11-13
     */
    public Attendee getAttendeeByUserName(String username) {
        User user = this.getUserByUserName(username);
        if (user.getClass().equals(Attendee.class)) {
            return (Attendee)user;
        }
        return null;
    }

    /**
     * @Description: Return Speaker by given <id>, if the user does not exist or is not Speaker, return null
     * @Param: [id]
     * @return: Speaker
     * @Author:
     * @Date: 2020-11-12
     */
    public Speaker getSpeakerById(int id) {
        User user = this.getUserById(id);
        if (user.getClass().equals(Speaker.class)) {
            return (Speaker)user;
        }
        return null;
    }

    /**
     * @Description: get speaker by given username
     * @Param: [username]
     * @return: Speaker
     * @Author:
     * @Date: 2020-11-13
     */
    public Speaker getSpeakerByUserName(String username) {
        User user = this.getUserByUserName(username);
        if (user.getClass().equals(Speaker.class)) {
            return (Speaker)user;
        }
        return null;
    }
    /**
     * @Description:
     * @Param: [username]
     * @return: Organizer
     * @Author:
     * @Date: 2020-11-13
     */
    public Organizer getOrganizerByUserName(String username) {
        User user = this.getUserByUserName(username);
        if (user.getClass().equals(Organizer.class)) {
            return (Organizer)user;
        }
        return null;
    }

    /**
     * @Description: Return Organizer by given <id>, if the user does not exist or is not Organizer, return null
     * @Param: [id]
     * @return: Organizer
     * @Author:
     * @Date: 2020-11-12
     */
    public Organizer getOrganizerById(int id) {
        User user = this.getUserById(id);
        if (user.getClass().equals(Organizer.class)) {
            return (Organizer)user;
        }
        return null;
    }


    // ===== Event: Hash=====
    /**
     * @Description: Add event to event list
     * @Param: [event]
     * @return: void
     * @Author:
     * @Date: 2020-11-28
     */
    public void addEvent(Event event) {
        String serData = gson().toJson(event);
        addToHash(EVENT_HASH, event.getEvent_id(), serData);
    }
    public void updateEvent(Event event) {
        String serData = gson().toJson(event);
        updateToHash(EVENT_HASH, event.getEvent_id(), serData);
    }
    /**
     * @Description: Get list of all events. *This is method may lag the performance.
     * @Param: []
     * @return: java.util.List<Event>
     * @Author:
     * @Date: 2020-11-28
     */
    public List<Event> getEventList() {
        List<String> dateList = new ArrayList<>(getAllFromHash(EVENT_HASH).values());
        List<Event> eventList = new ArrayList<>();
        Gson gson = gson();
        for (String serData : dateList) {
            try {
                eventList.add(gson.fromJson(serData, Event.class));
            } catch(Exception e) {
            }
        }
        return eventList;
    }
    /**
     * @Description: Get event by given id
     * @Param: [id]
     * @return: Event
     * @Author:
     * @Date: 2020-11-28
     */
    public Event getEventById(int id) {
        String serData = getByIdFromHash(EVENT_HASH, id);
        return gson().fromJson(serData, Event.class);
    }


    // ===== Room: Hash=====
    /**
     * @Description: add room to room list
     * @Param: [room]
     * @return: void
     * @Author:
     * @Date: 2020-11-28
     */
    public void addRoom(Room room) {
        String serData = gson().toJson(room);
        addToHash(ROOM_HASH, room.getRid(), serData);
    }
    public void updateRoom(Room room) {
        String serData = gson().toJson(room);
        updateToHash(ROOM_HASH, room.getRid(), serData);
    }
    /**
     * @Description: get list of rooms
     * @Param: []
     * @return: java.util.List<Room>
     * @Author:
     * @Date: 2020-11-28
     */
    public List<Room> getRoomList() {
        List<String> dateList = new ArrayList<>(getAllFromHash(ROOM_HASH).values());
        List<Room> roomList = new ArrayList<>();
        Gson gson = gson();
        for (String serData : dateList) {
            try {
                roomList.add(gson.fromJson(serData, Room.class));
            } catch(Exception e) {
            }
        }
        return roomList;
    }
    /**
     * @Description: get room by given id
     * @Param: [id]
     * @return: Room
     * @Author:
     * @Date: 2020-11-28
     */
    public Room getRoomById(int id) {
        String serData = getByIdFromHash(ROOM_HASH, id);
        return gson().fromJson(serData, Room.class);
    }
    /**
     * @Description: get room by given roomNum
     * @Param: [roomNum]
     * @return: Room
     * @Author:
     * @Date: 2020-11-28
     */
    public Room getRoomByRoomNum(String roomNum) {
        ArrayList<Room> roomList = (ArrayList<Room>) getRoomList();
        for (Room r : roomList) {
            if (r.getRoom_num().equals(roomNum)) {
                return r;
            }
        }
        return null;
    }


    // ===== Message: List =====
    /**
     * @Description: add Message to the Message List
     * @Param: [message]
     * @return: void
     * @Author:
     * @Date: 2020-11-12
     */
    public void addMessage(Message message) {
        String serData = gson().toJson(message);
        addToList(MESSAGE_LIST, serData);

    }
    /**
     * @Description: get List of all messages
     * @Param: []
     * @return: java.util.List<Message>
     * @Date: 2020-11-28
     */
    private List<Message> getMessageList() {
        List<String> dateList = new ArrayList<>(getAllFromList(MESSAGE_LIST));
        List<Message> messageList = new ArrayList<>();
        Gson gson = gson();
        for (String serData : dateList) {
            try {
                messageList.add(gson.fromJson(serData, Message.class));
            } catch(Exception e) {
            }
        }
        return messageList;
    }
    /**
     * @Description: Return the List of messages related to <userId>; Cannot add message
     * @Param: [userId]
     * @return: java.util.List<Message>
     * @Date: 2020-11-11
     */
    public List<Message> getAllMessageListByUserId(int userId) {
        ArrayList<Message> messageList = (ArrayList<Message>) getMessageList();
        List<Message> ret = new ArrayList<>();
        for (Message m : messageList) {
            if (m.getReceiverId() == userId || m.getSenderId() == userId) {
                ret.add(m);
            }
        }
        return ret;
    }
    /**
     * @Description: Return the List of Sent messages related to <userId>; Cannot add message
     * @Param: [userId]
     * @return: java.util.List<Message>
     * @Author:
     * @Date: 2020-11-14
     */
    public List<Message> getSentMessageListByUserId(int userId) {
        ArrayList<Message> messageList = (ArrayList<Message>) getMessageList();
        List<Message> ret = new ArrayList<>();
        for (Message m : messageList) {
            if (m.getSenderId() == userId) {
                ret.add(m);
            }
        }
        return ret;
    }
    /**
     * @Description: Return the List of Received messages related to <userId>; Cannot add message
     * @Param: [userId]
     * @return: java.util.List<Message>
     * @Author:
     * @Date: 2020-11-14
     */
    public List<Message> getReceivedMessageListByUserId(int userId) {
        ArrayList<Message> messageList = (ArrayList<Message>) getMessageList();
        List<Message> ret = new ArrayList<>();
        for (Message m : messageList) {
            if (m.getReceiverId() == userId) {
                ret.add(m);
            }
        }
        return ret;
    }



    /** Testing */
    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        gateway.init();
        gateway.testCases();
//        gateway.printDataBase();
    }

    /** Enable '-ea' in VM option in config before testing*/
    private void testCases () {
        System.out.println("Gateway: Testing...");
        testUser ();
        testEvent ();
        testRoom ();
        testMessage();
        System.out.println("Gateway: All tests passed");
    }
    /** This method is used for test    */
    public void printDataBase () {
        Jedis jedis = getJedis();

        System.out.println("DataBase: Displaying DATA:");
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
        System.out.println("+ UserNextId: " + jedis.get(NEXT_USER_ID));
        System.out.println("+ EventNextId: " + jedis.get(NEXT_EVENT_ID));
        System.out.println("+ RoomNextId: " + jedis.get(NEXT_ROOM_ID));
        System.out.println("+ User List");
        getUserList().forEach((u) -> System.out.println("   - " + u.toString()));
        System.out.println("+ Event List");
        getEventList().forEach((e) -> System.out.println("  - " + e.toString()));
        System.out.println("+ Room List");
        getRoomList().forEach((r) -> System.out.println("   - " + r.toString()));
        System.out.println("+ Message List");
        getMessageList().forEach((m) -> System.out.println("    - " + m.toString()));
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");

        // Close
        closeJedis(jedis);
    }

    private void testUser () {
        addUser(new Attendee(0, "123123", "Jim"));
        addUser(new Organizer(1, "234234", "JimO"));
        assert (getUserById(0).getUserName().equals("Jim"));
        assert (getOrganizerById(0) == null);
        assert (getOrganizerById(1).getUserName().equals("JimO"));
        assert (getUserList().get(1).getClass().equals(Organizer.class));
    }

    private void testEvent () {
        addEvent(new Event(LocalDateTime.now(), 0,0, "First Event", 0));
        addEvent(new Event(LocalDateTime.now(), 1,125, "Second Event", 0));
        assert (getEventById(0).getTitle().equals("First Event"));
        assert (getEventList().get(1).getSpeakerId() == 125);
    }

    private void testRoom () {
        addRoom(new Room("123313", 0));
        assert (getRoomById(0).getRoom_num().equals("123313"));
        assert (getRoomByRoomNum("123313").getRid() == 0);
    }

    private void testMessage () {
        boolean check = false;
        List<Message> messageList= getSentMessageListByUserId(0);
        for(Message message : messageList) {
            check = (message.getInfo().equals("Hello Message") || check);
        }
        if (check) {
            Message m = new Message("Hello Message", 0, 1);
            addMessage(m);
        }
        messageList= getSentMessageListByUserId(0);
        check = false;
        for(Message message : messageList) {
            check = (message.getInfo().equals("Hello Message") || check);
        }
        assert (check);
    }
}
