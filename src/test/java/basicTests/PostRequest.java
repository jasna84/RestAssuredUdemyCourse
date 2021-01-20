package basicTests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostRequest {

    @Test
    public void verifyPostRequest() {

        RestAssured.baseURI = "https://maps.googleapis.com";

        String payload = "{"+
                "\"location\": { "+
                "\"lat\": -33.866971123445,"+
                "\"lng\": 151.1958750"+
                "},"+
                "\"accuracy\": 50,"+
                "\"name\": \"Google Shoes!\","+
                "\"phone_number\": \"(02) 9374 4000\","+
                "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
                "\"types\": [\"shoe_store\"],"+
                "\"website\": \"http://www.google.com.au/\","+
                "\"language\": \"en-AU\""+
                "}";

        System.out.println(payload);

        given().
                queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
                body(payload).
                when().
                post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().body("status", equalTo("OK"));
    }
}
