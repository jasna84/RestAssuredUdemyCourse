package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestUtils {

    private static Logger log = LogManager.getLogger(TestUtils.class.getName());

    public static String getJSONResponseString(Response response) {
        log.info("Converting json response to string");
        String stringResponse = response.getBody().asString();
        log.debug(stringResponse);
        return stringResponse;
    }

    public static JsonPath jSONParser(String response) {
        log.info("Parsing response to json");
        JsonPath jsonResponse = new JsonPath(response);
        log.debug(jsonResponse);
        return jsonResponse;
    }

    public static XmlPath xmlParser(String response) {
        log.info("Parsing response response to xml");
        XmlPath xmlResponse = new XmlPath(response);
        log.debug(xmlResponse);
        return xmlResponse;
    }

    public static int getStatusCode(Response response) {
        log.info("Getting status code");
        int statusCode = response.getStatusCode();
        log.debug(statusCode);
        return statusCode;
    }

    public static String getStatusMessage(Response response) {
        log.info("Getting status message");
        String statusMessage = response.getStatusLine();
        log.debug(statusMessage);
        return statusMessage;
    }
}
