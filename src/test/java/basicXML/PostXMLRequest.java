package basicXML;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class PostXMLRequest {

        @Test
        public void verifyResponse() throws IOException {

            String requestBody = generateString("PostXMLPayload.xml");

            RestAssured.baseURI = "https://maps.googleapis.com";
            Response res = given().
                    queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
                    body(requestBody).
                    when().
                    post("/maps/api/place/add/xml").
                    then().assertThat().statusCode(200).

                    extract().response();

            String response = res.asString();
            System.out.println(response);

            XmlPath xmlResponse = new XmlPath(response);
            String placeId = xmlResponse.get("PlaceAddResponse.place_id");
            System.out.println("*********************");
            System.out.println(placeId);

        }

        public static String generateString(String filename) throws IOException{
            String filePath = System.getProperty("user.dir")+"\\Payloads\\"+filename;
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }

    }
