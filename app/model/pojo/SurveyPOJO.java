package model.pojo;

public class SurveyPOJO {
    private final SurveyMetadataPOJO metadata;
    private String description;
    private String resourceUrl;

    public SurveyPOJO(SurveyMetadataPOJO metadata, String description, String resourceUrl) {
        this.metadata = metadata;
        this.description = description;
        this.resourceUrl = resourceUrl;
    }

    /**
     * We can't have multiple surveys with the same id.
     * This is guaranteed by the UUID from the metadata obj, and the unavailability of a setMetadata method.
     * As a safety, the surveys are kept into a set, this guarantees that there are no duplicates.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SurveyPOJO)) {
            return false;
        }
        return metadata.getUniqueID().equals(((SurveyPOJO) obj).metadata.getUniqueID());
    }

    public SurveyMetadataPOJO getMetadata() {
        return metadata;
    }
}
