package gateway;

import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: group_0173
 * @description: Gateway operating one field-value in database with type of hash.
 * @create: 2020-12-03 20:03
 **/
public class HashGateway<T> extends Gateway<T>{

    private String hashKey;
    private String idKey;

    public HashGateway(String idKey, String hashKey, Type type, boolean genericEnable) {
        super(type, genericEnable);
        this.idKey = idKey;
        this.hashKey = hashKey;
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

    /**
     * @Description: increate the id
     * @return the next id
     */
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

    /** Helper Func, adding (id, value) to the <key>map */
    private Map<String, String> getAll () {
        Jedis jedis = getJedis();
        Map<String, String> map = jedis.hgetAll(this.hashKey);
        closeJedis(jedis);
        return map;
    }

    /** Hash Methods */
    public void add (int id, T obj) {
        String value = serialize(obj);
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
    public void update (int id, T obj) {
        add(id, obj);
    }
    public T get (int id) {
        Jedis jedis = getJedis();
        String value = jedis.hget(this.hashKey, String.valueOf(id));
        closeJedis(jedis);
        return deserialize(value);
    }
    public void delete (int id) {
        Jedis jedis = getJedis();
        jedis.hdel(this.hashKey, String.valueOf(id));
        closeJedis(jedis);
    }
    public List<T> getList() {
        List<String> dateList = new ArrayList<>(getAll().values());
        List<T> list = new ArrayList<>();
        for (String data : dateList) {
            try {
                list.add((T)deserialize(data));
            } catch(Exception e) {
            }
        }
        return list;
    }
}
