package org.davinatw.apiTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;

import java.util.List;
import static org.hamcrest.Matchers.*;

public class apiStepDef {
    Response response;

    String appID = "63a804408eb0cb069b57e43a";

    @Given("user sends GET request to get all users")
    public void sendsGetRequestAllUsers() {
        response = RestAssured
                .given()
                .header("app-id", appID)
                .log().all()
                .when()
                .get("/user");

    }

    @Then("response status code should be {int}")
    public void responseStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("response body should match json schema {string}")
    public void responseBodyShouldMatchJsonSchema(String schemaFileName) {
        File schema = new File(
                "src/test/resources/API/jsonSchema/" + schemaFileName
        );

        response.then().assertThat()
                .body(matchesJsonSchema(schema));
    }

    @Given("user sends GET request using id {string}")
    public void sendsGetRequestUsingId(String id) {
        response = RestAssured
                .given()
                .header("app-id", appID)
                .when()
                .get("/user/" + id);
    }

    @Given("user sends POST request to create an account")
    public void sendsPostRequestToCreateAnAccount() {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("firstName", "Dap");
        bodyJSON.put("lastName", "Phin");
        bodyJSON.put("email","dor1@yahoo.com");

        response = RestAssured
                .given()
                .header("app-id", appID)
                .header("Content-Type", "application/json")
                .body(bodyJSON.toString())
                .when()
                .post("/user/create");

    }

    @Given("user sends PUT request to update all the information for user with id {string}")
    public void sendsPUTRequest(String id) {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("firstName", "Dap");
        bodyJSON.put("lastName", "Lee");

        response = RestAssured
                .given()
                .log().all() // This will show the EXACT URL being called in your terminal
                .header("app-id", appID)
                .header("Content-Type", "application/json")
                .body(bodyJSON.toString())
                .when()
                .put("/user/" + id);

    }

    @Given("user sends DELETE request using id {string}")
    public void sendsDELETERequest(String id) {
        response = RestAssured
                .given()
                .header("app-id", appID)
                .when()
                .delete("/user/" + id);
    }

    @And("response body should contain deleted user id {string}")
    public void responseBodyShouldContainDeletedUserId(String id) {
        String actualId = response.jsonPath().getString("id");
        assertEquals(id, actualId);
    }

    @Given("user sends GET request to get list of tags")
    public void sendsGetRequestAllTags() {
        response = RestAssured
                .given()
                .header("app-id", appID)
                .log().all()
                .when()
                .get("/tag");
    }


    @And("response body should contain a data array")
    public void responseBodyShouldContainADataArray() {
        response.then()
                .body("data", instanceOf(List.class));


    }
}
