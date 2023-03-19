import model.UserBodyRequestModel;
import model.UserBodyResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresInTests {


    @Test
    void patchNameAndJobTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        data.setJob("nuclear safety inspector");
        UserBodyResponseModel response = given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getName()).isEqualTo("Homer Simpson");
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector");
    }

    @Test
    void patchOnlyNameTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        UserBodyResponseModel response = given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getName()).isEqualTo("Homer Simpson");
    }

    @Test
    void patchOnlyJobTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setJob("nuclear safety inspector");
        UserBodyResponseModel response = given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector");

    }

    @Test
    void patchWithountBodyTest() {
        given()
                .log().uri()
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void patchAddWifeTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        data.setJob("nuclear safety inspector");
        data.setWife("Marge Simpson");
        UserBodyResponseModel response = given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector");
        assertThat(response.getName()).isEqualTo("Homer Simpson");
        assertThat(response.getWife()).isEqualTo("Marge Simpson");

    }

}
