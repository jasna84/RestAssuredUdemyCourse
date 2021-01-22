package utils;

public class URL {

    public static final String URL = "http://localhost:8080";

    public static String getEndpoint() {
        return URL;
    }

    public static String getEndpoint(String resource) {
        return URL + resource;
    }
}
