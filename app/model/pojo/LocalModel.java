package model.pojo;

import java.util.*;

public class LocalModel {
    public final Set<SurveyPOJO> surveys;


    public LocalModel() {
        surveys = new HashSet<SurveyPOJO>();


        /**
         * Mock db dataset. Added nulls to test system robustness.
         *
         */
        SurveyMetadataPOJO meta = new SurveyMetadataPOJO("Srv1", SurveyTopic.ADVERTISING, new Long(10));
        SurveyPOJO survey = new SurveyPOJO(meta, "Survey 1 description ", "http://www.admedo.com/blog/online-advertising-survey-results-part-1");
        surveys.add(survey);

        meta = new SurveyMetadataPOJO("Srv2", SurveyTopic.AGRICULTURE, new Long(10000));
        survey = new SurveyPOJO(meta, "Survey 2 description ", "http://ec.europa.eu/eurostat/statistics-explained/index.php/Agricultural_census_2010_-_main_results");
        surveys.add(survey);

        meta = new SurveyMetadataPOJO("Srv3", SurveyTopic.AUTO_INDUSTRY, null);
        survey = new SurveyPOJO(meta, "Survey 3 description ", "http://www.strategyand.pwc.com/reports/2013-us-automotive-industry-survey-confidence-index");
        surveys.add(survey);

        meta = new SurveyMetadataPOJO("Srv4", SurveyTopic.CONSUMER_RESEARCH, new Long(-1000));
        survey = new SurveyPOJO(meta, "Survey 4 description ", null);
        surveys.add(survey);

        meta = new SurveyMetadataPOJO("Srv5", SurveyTopic.CUSTOMER_SATISFACTION, new Date().getTime());
        survey = new SurveyPOJO(meta, "Survey 5 description ", "http://www.dodea.edu/datacenter/surveys.cfm");
        surveys.add(survey);

        meta = new SurveyMetadataPOJO("Srv6", SurveyTopic.CUSTOMER_SATISFACTION, new Long(1900000099));
        survey = new SurveyPOJO(meta, "Survey 6 description ", "http://www.dodea.edu/datacenter/upload/CSS_2010_ExecutiveSummary.pdf");
        surveys.add(survey);

        meta = new SurveyMetadataPOJO("Srv7", SurveyTopic.AGRICULTURE, new Long(10000));
        survey = new SurveyPOJO(meta, "Survey 7 description ", "Survey resource");
        surveys.add(survey);
    }


    public List<SurveyMetadataPOJO> getMetadata() {
        List<SurveyMetadataPOJO> metadata = new ArrayList<SurveyMetadataPOJO>();
        for (SurveyPOJO survey : surveys) {
            metadata.add(survey.getMetadata());
        }
        return metadata;
    }

    public List<SurveyMetadataPOJO> getMetadataByTopic(SurveyTopic topic) {
        List<SurveyMetadataPOJO> metadata = new ArrayList<SurveyMetadataPOJO>();
        for (SurveyPOJO survey : surveys) {
            SurveyMetadataPOJO meta = survey.getMetadata();
            if (topic.equals(meta.getTopic())) {
                metadata.add(meta);
            }
        }
        return metadata;
    }

    /**
     * Will return the survey count from the db grouped by topic
     * eg:
     * ADVERTISING : 2,
     * AGRICULTURE : 1,
     *
     * @return
     */
    public Map<SurveyTopic, Integer> getSurveyCountByTopic() {
        Map<SurveyTopic, Integer> returnMap = new HashMap<SurveyTopic, Integer>();

        //populate the map from the SurveyType enum, this makes sure we display the count even if we don't have any surveys of that type
        for (SurveyTopic topic : SurveyTopic.values()) {
            returnMap.put(topic, 0);
        }

        // build the count map
        for (SurveyPOJO survey : surveys) {
            SurveyTopic currentTopic = survey.getMetadata().getTopic();
            returnMap.put(currentTopic, returnMap.get(currentTopic) + 1);
        }
        return returnMap;
    }

    public SurveyPOJO getSurvey(String uuid) {
        for (SurveyPOJO survey : surveys) {
            if (survey.getMetadata().getUniqueID().equals(uuid)) {
                return survey;
            }
        }
        return null;
    }
}
