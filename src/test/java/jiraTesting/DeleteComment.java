package jiraTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DeleteComment {

    @Test
    public void verifyResponse() throws IOException, InterruptedException {

        String requestBody = generateString("JiraLogin.json");

        // Login JIRA

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("/rest/auth/1/session").
                then().assertThat().statusCode(200).

                extract().response();

        String response = res.asString();

        JsonPath jsonRes = new JsonPath(response);
        String sessionID = jsonRes.getString("session.value");

        //Create Issue
        String createBugBody = generateString("CreateBug.json");
        Response createResponse = given().
                contentType(ContentType.JSON).
                header("cookie", "JSESSIONID=" + sessionID+"").
                body(createBugBody).
                when().
                post("/rest/api/2/issue").
                then().assertThat().statusCode(201).log().all().

                extract().response();

        JsonPath createResJson = new JsonPath(createResponse.asString());
        String issueID = createResJson.getString("id");

        //Add Comment
        String createCmntBody = generateString("AddCmnt.json");
        Response addCmntResponse = given().
                contentType(ContentType.JSON).
                header("cookie", "JSESSIONID=" + sessionID+"").
                body(createCmntBody).
                when().
                post("/rest/api/2/issue/"+issueID+"/comment").
                then().assertThat().statusCode(201).log().all().

                extract().response();

        JsonPath addCmntResJson = new JsonPath(addCmntResponse.asString());
        String cmntId = addCmntResJson.getString("id");

        Thread.sleep(3000);

        //Delete Comment
        Response deleteCmntResponse = given().
                contentType(ContentType.JSON).
                header("cookie", "JSESSIONID=" + sessionID+"").
                when().
                delete("/rest/api/2/issue/"+issueID+"/comment/"+cmntId).
                then().assertThat().statusCode(204).log().all().

                extract().response();
    }

    public static String generateString(String filename) throws IOException {
        String filePath = System.getProperty("user.dir")+"\\Payloads\\"+filename;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
