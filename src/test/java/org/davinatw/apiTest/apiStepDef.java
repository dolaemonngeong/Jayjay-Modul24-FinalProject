package org.davinatw.apiTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;

public class apiStepDef {
    Response response;

    String apiKey = "be1160c4ec0a512aa0824b51c55abc5374975884a36c0fa330418db868955231";
//    String apiKey = "63a804408eb0cb069b57e43a");

    @Given("user sends GET request to get all users")
    public void sendsGetRequestAllUsers() {
        response = RestAssured
                .given()
                .when()
                .get("/users");

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

    @Given("user sends GET request using id {int}")
    public void sendsGetRequestUsingId(int id) {
        response = RestAssured
                .given()
                .header("Authorization","Bearer " + apiKey)
                .when()
                .get("/users/" + id);
        System.out.println("ID received from Feature file: " + id);
    }


    @Given("user sends GET request to get all posts")
    public void sendsGetRequestToGetAllPosts() {
        response = RestAssured
                .given()
                .when()
                .get("/posts");
    }

    @Given("user sends GET request to get all comments")
    public void sendsGetRequestToGetAllComments() {
        response = RestAssured
                .given()
                .when()
                .get("/comments");
    }

    @Given("user sends PATCH request to update user with id {int} and status {string}")
    public void sendsPATCHRequestStatus(int id, String status) {
        JSONObject body = new JSONObject();
        body.put("status", status);

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .patch("/users/" + id);
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response Body:\n" + response.getBody().asString());

    }



    @Given("user sends PUT request to update all the information for user with id {int}")
    public void sendsPUTRequest(int id) {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("name", "Davina T W");
        bodyJSON.put("gender", "female");
        bodyJSON.put("email", "johndoe1@gmail.com");
        bodyJSON.put("status", "active");

        response = RestAssured
                .given()
                .log().all() // This will show the EXACT URL being called in your terminal
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(bodyJSON.toString())
                .when()
                .put("/users/" + id);
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response Body:\n" + response.getBody().asString());

    }


}
