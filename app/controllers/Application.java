package controllers;

import model.JsonHelper;
import model.pojo.LocalModel;
import model.pojo.SurveyMetadataPOJO;
import model.pojo.SurveyPOJO;
import model.pojo.SurveyTopic;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main application controller,
 * each method maps to a specific route : see conf.routes
 */
public class Application extends Controller {
    /* This is assumed to be populated by a db.
     * However since the db deployment is outside of the scope for this project the localModel is mocked.*/
    private static final LocalModel model = new LocalModel();

    public static Result index() {
        String newLine = System.getProperty("line.separator");
        StringBuilder response = new StringBuilder();
        response.append("/metadata                                 | getInfo about the available surveys" + newLine);
        response.append("/metadataByTopic?topic=<valid_topic_name> | metadata about the surveys of a topic" + newLine);
        response.append("/count                                    | available surveys for every topic" + newLine);
        response.append("/survey?id=<valid_survey_UUID>            | get the survey " + newLine);
        return ok(response.toString());
    }

    /**
     * /metadata
     */
    public static Result fetchMetadata() {
        List<SurveyMetadataPOJO> metadata = model.getMetadata();
        String ret = JsonHelper.getInstance().asJson(metadata);
        return ok(ret);
    }

    /**
     * /metadataByTopic
     */
    public static Result fetchMetadataByTopic(String topic) {
        try {
            SurveyTopic queriedTopic = SurveyTopic.getByStringValue(topic);
            List<SurveyMetadataPOJO> metadata = model.getMetadataByTopic(queriedTopic);
            String ret = JsonHelper.getInstance().asJson(metadata);
            return ok(ret);
        } catch (IllegalArgumentException e) {
            List<SurveyTopic> somethingList = Arrays.asList(SurveyTopic.values());
            return badRequest("Bad topic value, try one of these " + somethingList.toString());
        }
    }

    /**
     * /count
     */
    public static Result fetchCount() {
        String response = JsonHelper.getInstance().asJson(model.getSurveyCountByTopic());
        return ok(response);
    }

    /**
     * /survey
     */
    public static Result getSurvey(String id) {
            SurveyPOJO survey = model.getSurvey(id);
        if (survey == null) {
            return badRequest("Bad ID ");
        }

        String response = JsonHelper.getInstance().asJson(survey);
        return ok(response);
    }

}