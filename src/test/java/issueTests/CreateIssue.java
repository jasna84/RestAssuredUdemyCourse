package issueTests;

import core.Assertions;
import core.BaseTest;
import core.RESTCalls;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PayloadConverter;
import java.io.IOException;

public class CreateIssue extends BaseTest {

    private String sessionId;
    Response response;

    private static final Logger log = LogManager.getLogger(CreateIssue.class.getName());

    @BeforeMethod
    public void setUp() throws IOException {
        sessionId = doLogin();
    }

    @Test
    public void verifyCreateIssue() throws IOException {

        log.info("Starting verify create issue test");
        String URI = "/rest/api/2/issue";
        String createIssuePayload = PayloadConverter.generateString("CreateBug.json");

        response = RESTCalls.POSTRequest(URI, createIssuePayload, sessionId);
        Assertions.verifyStatusCode(response, 201);

    }

}
