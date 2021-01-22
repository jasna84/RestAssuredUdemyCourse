package core;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.TestUtils;

public class Assertions {

    private static Logger log = LogManager.getLogger(Assertions.class.getName());

    public static void verifyTrue(boolean flag) {
        Assert.assertTrue(flag);
    }

    public static void verifyFalse(boolean flag) {
        Assert.assertFalse(flag);
    }

    public static void verifyStatusCode(Response response, int status) {
        Assert.assertEquals(TestUtils.getStatusCode(response), status);
    }

    public static void verifyStatusMessage(Response response, String message) {
        Assert.assertEquals(TestUtils.getStatusMessage(response), message);
    }

}
