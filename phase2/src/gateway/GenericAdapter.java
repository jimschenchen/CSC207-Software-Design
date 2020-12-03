package gateway;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @program: group_0173
 * @description: UserAdapter is a Gson Adapter for serializing User Class and its sub Classes
 * @reference: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
 * @reference: https://medium.com/@ezralazuardy/gson-custom-interface-adapter-3f3b863e063d
 * @create: 2020-11-27 11:09
 **/
public class GenericAdapter implements JsonSerializer, JsonDeserializer{

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    public Object deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        Class klass = getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
    }

    public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }

    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
