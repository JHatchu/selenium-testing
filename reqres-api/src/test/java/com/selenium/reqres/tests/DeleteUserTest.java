package com.selenium.reqres.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseApiTest {

    @Test
    public void deleteUser() {
        given()
                .when().delete("/users/2")
                .then().statusCode(204);
    }
}
