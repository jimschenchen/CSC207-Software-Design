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
 * @description:
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
    public void addUser(User user) {
        UserBean userBean = new UserBean(user);
        String serData = gson(User.class, new UserAdapter()).toJson(userBean);
        addToHash(USER_HASH, user.getUser_id(), serData);
    }
    public User getUserById(int id) {
        String serData = getByIdFromHash(USER_HASH, id);
        UserBean userBean = gson(User.class, new UserAdapter()).fromJson(serData, UserBean.class);
        User user = userBean.convertToUser();
        return user;
    }
    public List<User> getUserList() {
        List userList = new ArrayList();
        // TODO: DO WE NEED TO GET LIST OF USERS?
        return userList;
    }

    // TODO: WHY I USE NONSQL??? HOW TO SOLVE GET BY USERNAME? DAMN!





    // ===== Message: List =====



    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        gateway.init();
        User u = new Attendee(0, "jim", "123123");
        // gateway.addUser(u);
        gateway.getAllFromHash(USER_HASH);
        System.out.println(gateway.getUserById(0));
    }











}
