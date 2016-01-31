package model;

import com.google.gson.*;
import model.pojo.SurveyTopic;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonHelper {
    private static JsonHelper instance = new JsonHelper();
    private final Gson gson;

    public static JsonHelper getInstance() {
        return instance;
    }

    private JsonHelper() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SurveyTopic.class, new SurveyTopicSerializer());
        builder.setPrettyPrinting();
        gson = builder.create();
    }


    public String asJson(List<? extends Object> list) {
        return gson.toJson(list);
    }

    public String asJson(Map map) {
        return gson.toJson(map);
    }

    public String asJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * It was agreed that the survey topics will be returned to the user (and passed in by the user)
     * in a human readable form (SurveyTopic.topic:String) instead of the enum value name (variable name).
     * This is what this custom serializer does.
     */
    private class SurveyTopicSerializer implements JsonSerializer<SurveyTopic> {
        @Override
        public JsonElement serialize(SurveyTopic topic, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(topic.toString());
        }
    }
}
