package com.selenium.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUserTest extends BaseApiTest {

    @Test(priority = 1)
    public void testGetListUsers() {
        given().queryParam("page", 2)
                .when().get("/users")
                .then().statusCode(200)
                .body("page", equalTo(2))
                .body("data", not(empty()));
    }

    @Test(priority = 2)
    public void testGetSingleUser() {
        given().when().get("/users/2")
                .then().statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", notNullValue());
    }

    @Test(priority = 3)
    public void testGetSingleUserNotFound() {
        given().when().get("/users/23")
                .then().statusCode(404);
    }
}
