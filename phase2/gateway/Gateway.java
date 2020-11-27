import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.lang.reflect.Type;

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

    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        gateway.init();
        System.out.println(gateway.getNextUserId());
    }

    // String Type
    /** Helper Func for get <key> id and increase it by 1 */
    private int getNextId(String key) {
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
        return getNextId("next_user_id");
    }

    /** Return the next event id and self increase by 1 */
    public int getNextEventId() {
        return getNextId("next_event_id");
    }

    /** Return the next room id and self increase by 1 */
    public int getNextRoomId() {
        return getNextId("next_room_id");
    }












}
