package ru.practikum.api.steps;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;
import ru.practikum.api.dto.CreateUserRequest;
import ru.practikum.api.dto.LoginUserRequest;
import ru.practikum.api.register.GenerateUser;

import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Send post to register user with info")
    public ValidatableResponse createUser(GenerateUser generateUser) {
        CreateUserRequest createUserRequest = new CreateUserRequest(generateUser.getName(), generateUser.getPassword(), generateUser.getEmail());
        return given()
                .contentType(ContentType.JSON)
                .body(createUserRequest)
                .when()
                .post("/api/auth/register")
                .then();
    }
    @Step("Delete user with access token")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }
    @Step("Login user with email and password")
    public ValidatableResponse loginUserLogopass(String email, String password) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(email);
        loginUserRequest.setPassword(password);
        return given()
                .contentType(ContentType.JSON)
                .body(loginUserRequest)
                .when()
                .post("/api/auth/login")
                .then();
    }
    public String getUserAccessToken(GenerateUser generateUser){
        return loginUserLogopass(generateUser.getEmail(), generateUser.getPassword())
                .extract().body().path("accessToken");
    }

}
