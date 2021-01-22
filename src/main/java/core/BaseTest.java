package core;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PayloadConverter;
import utils.TestUtils;
import utils.URL;

import java.io.IOException;

public class BaseTest {

    private static Logger log = LogManager.getLogger(BaseTest.class.getName());

    @Test
    public String doLogin() throws IOException {

        Response response;
        log.info("Starting test: doLogin" );

        String loginPayload = PayloadConverter.generateString("JiraLogin.json");
        String endpointURI = URL.getEndpoint("/rest/auth/1/session");
        response = RESTCalls.POSTRequest(endpointURI,loginPayload);

        String strResponse = TestUtils.getJSONResponseString(response);
        JsonPath jsonRes = TestUtils.jSONParser(strResponse);
        String sessionID = jsonRes.getString("session.value");

        log.info("Jira sessionid is: " + sessionID );
        return sessionID;
    }
}
