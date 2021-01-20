package basicTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidateResponse {

    @Test
    public void verifyResponse() {

        RestAssured.baseURI = "https://maps.googleapis.com";;

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("type", "restaurant").
                param("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("The Little Snail Restaurant")).and().
                body("results[13].name", equalTo("Din Tai Fung The Star")).and().
                body("results[5].vicinity", equalTo("48 Pirrama Road, Pyrmont")).and().
                header("Server", "scaffolding on HTTPServer2");
        System.out.println("Request is executed successfully");
    }
}
