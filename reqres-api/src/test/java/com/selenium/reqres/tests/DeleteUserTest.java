
package com.selenium.reqres.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {

    @Test
    public void deleteUser() {
        RestAssured.baseURI = "https://reqres.in/api";
        given()
            .when()
            .delete("/users/2")
            .then()
            .statusCode(204);

        System.out.println("Delete returned 204 as expected.");
    }
}
