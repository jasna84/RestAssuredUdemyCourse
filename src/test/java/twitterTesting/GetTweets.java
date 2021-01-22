package twitterTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetTweets {

    String consumerKey = "PBfewhhp10o3ITJLaIRy1nLMg";
    String consumerSecret = "DrRGwimnjLJ96UbYBYRuQ3LvLkWRnyvQsRNr5b7CGt4jPc62t2";
    String accessToken ="3945395116-1R3LCXsNr9XU6M7cmAwYBtiNSFFP5Ad4tSoCyjB";
    String accessTokenSecret = "Ki1AFBTDP8kSHmDKPBWur1Jz3G04Y36hxEOndwhTa45Lr";

    @Test
    public void getFirstThreeTweets() throws IOException {

        RestAssured.baseURI = "https://api.twitter.com";
        Response res = given().
                auth().
                oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret).
                param("count", 3).log().all().

                when().
                get("/1.1/statuses/user_timeline.json").

                then().assertThat().statusCode(200).log().all().

                extract().response();
    }
}
