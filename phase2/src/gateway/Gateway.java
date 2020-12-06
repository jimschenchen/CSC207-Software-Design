package gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Type;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-12-03 17:56
 **/
public abstract class Gateway<T> {

    private static JedisPool jedisPool;

    static final String DATABASE_URL = Config.DATABASE_URL;
    static final int DATABASE_PORT = Config.DATABASE_PORT;
    static final String DATABASE_PASSWORD = Config.DATABASE_PASSWORD;

    private boolean genericEnable;
    private Gson gson;
    private Type type;

    /** Constructor */
    /**
    * @Description: Default constructor is only for testing
    * @Date: 2020-12-06
    */
    public Gateway() {
        initJedisPool();
    }
    public Gateway(Type type, boolean genericEnable) {
        setAttributes(type, genericEnable);
        initJedisPool();
    }
    private void setAttributes (Type type, boolean genericEnable) {
        this.type = type;
        this.genericEnable = genericEnable;
        if (genericEnable) {
            this.gson = buildGson(type, new GenericAdapter());
        } else {
            this.gson = buildGson();
        }
    }

    /** Private Method - Jedis Pool */
    private void initJedisPool() {
        if (jedisPool == null) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            jedisPool = new JedisPool(poolConfig, DATABASE_URL, DATABASE_PORT, 10000, DATABASE_PASSWORD);
            shutDownHook(); // ShutdownHook added
            System.out.println("Gateway: " + jedisPool.toString() + " has been Established");
            ping();
        }
    }
    private void close() {
        if(jedisPool != null){
            jedisPool.destroy();
            System.out.println("Gateway: Jedis Pool has been terminated");
        }
    }
    private void shutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> close()));
        System.out.println(getClass() + "Gateway: ShutdownHook has been Added");
    }

    /** Public Methods - Jedis */
    public Jedis getJedis() {
        try{
            return jedisPool.getResource();
        }catch(Exception e) {
            System.err.println(getClass() + "Gateway: Fail to get jedis from jedis pool\n");
            e.printStackTrace();
            return null;
        }
    }
    public void closeJedis(Jedis jedis) {
        if(null != jedis) jedis.close();
    }
    public void ping() {
        try(Jedis jedis = jedisPool.getResource()){
            System.out.println("Gateway: Jedis" + (jedis.ping().equals("PONG") ? "is connected" : "is disconnected"));
        }
    }

    /** Private Methods - Gson Builder */
    private Gson buildGson () {
        return new GsonBuilder().serializeNulls().create();
    }
    private Gson buildGson (Type type, Object typeAdapter) {
        return new GsonBuilder().registerTypeAdapter(type, typeAdapter).serializeNulls().create();
    }

    /** Public Methods - Gson */
    public String serialize (T obj) {
        if (genericEnable) {
            return new SerializationStrategy().serialize(obj, gson, type);
        } else {
            return gson.toJson(obj);
        }
    }
    public T deserialize (String data) {
        if (genericEnable) {
            return (T)new SerializationStrategy().deserialize(data, gson, type);
        } else {
            return (T)gson.fromJson(data, type);
        }
    }
}