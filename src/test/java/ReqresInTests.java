import model.UserData;
import org.junit.jupiter.api.Test;
import specs.UserSpecs;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInTests extends TestBase {

    @Test
    void checkFirstNameSingleUser() {
        step("GET /users/10 check first name", () -> {
            UserData data = UserSpecs.userRequestSpec
                    .when()
                    .get("/users/10")
                    .then()
                    .spec(UserSpecs.userResponseSpec)
                    .log()
                    .body()
                    .extract().as(UserData.class);
            assertEquals("Byron", data.getData().getFirstName());

        });
    }

    @Test
    void checkEmailListOfUser() {
        step("GET /users?page=2 check email", () -> {

            given()
                    .spec(UserSpecs.userRequestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(UserSpecs.userResponseSpec)
                    .log().body()
                    .body("data.findAll{it.email=~/.*?@reqres.in/}.email.flatten()",
                            hasItem("lindsay.ferguson@reqres.in"));
        });


    }

    @Test
    void checkFirstNameListOfUser() {
        step("GET /users?page=2 check first name", () -> {

            given()
                    .spec(UserSpecs.userRequestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(UserSpecs.userResponseSpec)
                    .log().body()
                    .body("data.find{it.id==10}.first_name",
                            is("Byron"));
        });


    }

    @Test
    void checkLastNameListOfUser() {
        step("GET /users?page=2 check last name", () -> {

            given()
                    .spec(UserSpecs.userRequestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(UserSpecs.userResponseSpec)
                    .log().body()
                    .body("data.find{it.first_name==~/Rachel/}.last_name",
                            is("Howell"));
        });


    }


}
