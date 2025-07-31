package com.example.user;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class AuthResourceTest {

    @Test
    public void testRegisterEndpoint() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"testuser\",\"email\":\"test@example.com\",\"password\":\"password123\",\"fullName\":\"Test User\"}")
        .when()
            .post("/api/auth/register")
        .then()
            .statusCode(201)
            .body("token", notNullValue())
            .body("user.username", is("testuser"))
            .body("user.email", is("test@example.com"));
    }

    @Test
    public void testLoginEndpoint() {
        // First register a user
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"loginuser\",\"email\":\"login@example.com\",\"password\":\"password123\",\"fullName\":\"Login User\"}")
        .when()
            .post("/api/auth/register")
        .then()
            .statusCode(201);

        // Then try to login
        given()
            .contentType(ContentType.JSON)
            .body("{\"usernameOrEmail\":\"loginuser\",\"password\":\"password123\"}")
        .when()
            .post("/api/auth/login")
        .then()
            .statusCode(200)
            .body("token", notNullValue())
            .body("user.username", is("loginuser"));
    }

    @Test
    public void testRegisterWithInvalidData() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"\",\"email\":\"invalid-email\",\"password\":\"123\",\"fullName\":\"\"}")
        .when()
            .post("/api/auth/register")
        .then()
            .statusCode(400);
    }
} 