
package com.selenium.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUserTest {

    @Test
    public void getUser() {
        RestAssured.baseURI = "https://reqres.in/api";
        Response resp = given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .extract().response();

        System.out.println(resp.asPrettyString());
    }
}
