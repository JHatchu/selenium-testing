package com.selenium.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseApiTest {

    @Test(priority = 1)
    public void createUser() {
        String body = "{ \"name\": \"John Doe\", \"job\": \"QA Engineer\" }";

        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("job", equalTo("QA Engineer"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }
}
