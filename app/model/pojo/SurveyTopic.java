package model.pojo;


import java.util.HashMap;
import java.util.Map;

public enum SurveyTopic {
    ADVERTISING ("Advertising"),
    AGRICULTURE ("Agriculture"),
    AUTO_INDUSTRY ("Automotive"),
    CONSUMER_RESEARCH ("Consumer Research"),
    CUSTOMER_SATISFACTION ("Customer satisfaction");



    private String topic;
    SurveyTopic(String topic){
        this.topic=topic;
        Lookup.lookup.put(topic,this);
    }

    @Override
    public String toString() {
        return topic;
    }

    /**
     * see Lookup.lookup
     * throws IllegalArgumentException as Enum.valueOf
     */
    public static SurveyTopic getByStringValue(String value){
        SurveyTopic topic = Lookup.lookup.get(value);
        if (topic == null){
            throw new IllegalArgumentException("Topic does not exist");
        }
        return topic;
    }

    private static class Lookup {
        /**
         * We will use reverse lookup from string topic to the SurveyTopic enum instead of using valueOf.
         * Less optimised but this allows to reasign string values to topic names without actually renamig the variables.
         */
        public static final Map<String, SurveyTopic> lookup = new HashMap<String, SurveyTopic>();
    }
}
