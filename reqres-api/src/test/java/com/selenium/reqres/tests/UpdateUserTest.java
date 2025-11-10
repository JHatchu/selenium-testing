
package com.selenium.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest {

    @Test
    public void updateUser() {
        RestAssured.baseURI = "https://reqres.in/api";
        String body = "{\n  \"name\": \"neo\",\n  \"job\": \"the one\"\n}";

        Response resp = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("neo"))
                .extract().response();

        System.out.println(resp.asPrettyString());
    }
}
