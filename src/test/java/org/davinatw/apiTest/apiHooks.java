package org.davinatw.apiTest;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class apiHooks {
    @Before("@api")
    public void setupApi() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
    }
}
