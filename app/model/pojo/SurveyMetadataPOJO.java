package model.pojo;


import java.util.UUID;

/**
 * Metadata class, contains info about the survey type,
 * Is separated into stand-alone class to provide the ability to show survey info without showing the actual survey results.
 */
public final class SurveyMetadataPOJO {
    private final String uniqueID;
    private final String title;
    private final SurveyTopic topic;
    //Long, this can be null if creation time is not available
    private final Long creationTimestamp;

    public SurveyMetadataPOJO(String title, SurveyTopic topic, Long creationTimestamp) {
        this.uniqueID = UUID.randomUUID().toString();
        this.title = title;
        this.topic = topic;
        this.creationTimestamp = creationTimestamp;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public SurveyTopic getTopic() {
        return topic;
    }
}
