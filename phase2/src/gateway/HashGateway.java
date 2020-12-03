package gateway;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-12-03 20:03
 **/
public class HashGateway extends Gateway{

    private String hashKey;
    private String idKey;
    private Gson gson;
    private Type type;
    //    private boolean genericEnable;

    public HashGateway(String idKey, String hashKey, Type type, boolean genericEnable) {
        super();
        this.idKey = idKey;
        this.hashKey = hashKey;
//        this.type = type;
//        this.genericEnable = genericEnable;
        if (genericEnable) {
            this.gson = buildGson(type, new GenericAdapter());
        } else {
            this.gson = buildGson();
        }
    }

    /** ID methods */

    /** Helper Func for get <key> id and increase it by 1 */
    public int getNextId () {
        Jedis jedis = getJedis();
        int ret = 0;
        String response = jedis.get(this.idKey);
        if (response == null) {
            jedis.set(this.idKey, "1");
        } else {
            ret = Integer.parseInt(response);
        }
        closeJedis(jedis);
        return ret;
    }
    public int getAndIncreaseNextId() {
        Jedis jedis = getJedis();
        int ret = 0;
        String response = jedis.get(this.idKey);
        if (response == null) {
            jedis.set(this.idKey, "1");
        } else {
            ret = Integer.parseInt(response);
            jedis.incr(this.idKey);
        }
        closeJedis(jedis);
        return ret;
    }

    /** Hash Methods */

    /** Helper Func, adding (id, value) to the <key>map */
    private void addToHash (int id, String value) {
        Jedis jedis = getJedis();
        String type = jedis.type(this.hashKey);
        if (type.equals("hash")) {
            jedis.hset(this.hashKey, String.valueOf(id), value);
        } else {
            jedis.del(this.hashKey);
            Map<String,String> map = new HashMap<>();
            map.put(String.valueOf(id), value);
            jedis.hmset(this.hashKey,map);
        }
        closeJedis(jedis);
    }
    private void updateToHash(int id, String value) {
        addToHash(id, value);
    }

    private Map<String, String> getAllFromHash() {
        Jedis jedis = getJedis();
        Map<String, String> map = jedis.hgetAll(this.hashKey);
        closeJedis(jedis);
        return map;
    }

    private String getByIdFromHash (int id) {
        Jedis jedis = getJedis();
        String value = jedis.hget(this.hashKey, String.valueOf(id));
        closeJedis(jedis);
        return value;
    }

    private void deleteFromHash(int id) {
        Jedis jedis = getJedis();
        jedis.hdel(this.hashKey, String.valueOf(id));
        closeJedis(jedis);
    }



//
//    private void addToList (String key, String value) {
//        Jedis jedis = getJedis();
//        String type = jedis.type(key);
//        if (!type.equals("list")) {
//            jedis.del(key);
//        }
//        jedis.lpush(key, value);
//        closeJedis(jedis);
//    }
//
//    private List<String> getAllFromList (String key) {
//        Jedis jedis = getJedis();
//        List<String> list = jedis.lrange(key,0, -1);
//        closeJedis(jedis);
//        return list;
//    }


    @Override
    public Gson getGson() {
        return this.gson;
    }
}
