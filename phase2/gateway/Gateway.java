import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Type;
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
            System.out.println("Gateway: Jedis is running: "+jedis.ping());
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


    // Hash type
    /** Helper Func, adding (id, value) to the <key>map */
    private void addToHash (String key, int id, String value) {
        Jedis jedis = getJedis();
        String type = jedis.type(key);
        if (type.equals("hash")) {
            jedis.hset(key, String.valueOf(id), value);
        } else {
            jedis.del(key);
            Map<String,String> map = new HashMap<String, String>();
            map.put(String.valueOf(id), value);
            jedis.hmset(key,map);
        }
        closeJedis(jedis);
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
        User user = userBean.convertToUser();
        return user;
    }
    /**
    * @Description: Get the Whole User List. *This is method may lag the performance.
    * @Param: []
    * @return: java.util.List<User>
    * @Author: 
    * @Date: 2020-11-28
    */
    public List<User> getUserList() {
        List<String> dateList = new ArrayList<String>(getAllFromHash(USER_HASH).values());
        List<User> userList = new ArrayList<>();
        Gson gson = gson(User.class, new UserAdapter());
        for (String serData : dateList) {
            try {
                UserBean userBean = gson.fromJson(serData, UserBean.class);
                userList.add(userBean.convertToUser());
            } finally {
                continue;
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




    
    
    
    

    // ===== Message: List =====



    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        gateway.init();
        gateway.printDataBase();
        // User u = new Attendee(0, "123", "Jim");
        // gateway.addUser(u);
//        System.out.println(gateway.getOrganizerByUserName("Jim"));
        // System.out.println(gateway.getUserById(0));
    }

    /** This method is used for test    */
    public void printDataBase () {
        Jedis jedis = getJedis();

        System.out.println("DataBase: Data for testing");
        System.out.println("---------- ---------- ---------- ----------");
        System.out.println("+ UserNextId: " + jedis.get(NEXT_USER_ID));
        System.out.println("+ EventNextId: " + jedis.get(NEXT_EVENT_ID));
        System.out.println("+ RoomNextId: " + jedis.get(NEXT_ROOM_ID));
        System.out.println("+ User List");
        getUserList().forEach((u) -> System.out.println("   - " + u.toString()));
//        System.out.println("+ Event List");
//        getEventList().forEach((e) -> System.out.println("  - " + e.toString()));
//        System.out.println("+ Room List");
//        getRoomList().forEach((r) -> System.out.println("   - " + r.toString()));
//        System.out.println("+ Message List");
//        getMessageList().forEach((m) -> System.out.println("    - " + m.toString()));
//        System.out.println("---------- ---------- ---------- ----------");

        // Close
        closeJedis(jedis);
    }
}
