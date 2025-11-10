package com.selenium.reqres.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseApiTest {

    @Test(priority = 1)
    public void updateUser() {
        String body = "{ \"name\": \"Jane Smith\", \"job\": \"Senior QA Engineer\" }";

        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().put("/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Jane Smith"))
                .body("job", equalTo("Senior QA Engineer"))
                .body("updatedAt", notNullValue());
    }

    @Test(priority = 2)
    public void partialUpdateUser() {
        String body = "{ \"job\": \"Lead QA Engineer\" }";

        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().patch("/users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo("Lead QA Engineer"))
                .body("updatedAt", notNullValue());
    }
}
