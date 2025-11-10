
package com.selenium.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.selenium.reqres.utils.WordDocGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {

    @Test
    public void createUser() {
        RestAssured.baseURI = "https://reqres.in/api";
        String body = "{\n  \"name\": \"morpheus\",\n  \"job\": \"leader\"\n}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .extract()
                .response();

        WordDocGenerator.createDoc("CreateUser", body, response.asPrettyString());
    }
}
