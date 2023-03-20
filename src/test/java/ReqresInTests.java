import model.UserBodyRequestModel;
import model.UserBodyResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.UserSpecs.userRequestSpec;
import static specs.UserSpecs.userResponseSpec;

public class ReqresInTests extends TestBase{


    @Test
    void patchNameAndJobTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        data.setJob("nuclear safety inspector");
        UserBodyResponseModel response =
                step("Get name and job data",()->
                given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class));
        step("Verify update response name",()->
        assertThat(response.getName()).isEqualTo("Homer Simpson"));
        step("Verify update job",()->
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector"));
    }

    @Test
    void patchOnlyNameTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        UserBodyResponseModel response =
                step("Get only name data",()->
                        given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class));
        step("Verify update name",()->
                assertThat(response.getName()).isEqualTo("Homer Simpson"));
    }

    @Test
    void patchOnlyJobTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setJob("nuclear safety inspector");
        UserBodyResponseModel response =
                step("Get only job data",()->
                        given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class));
        step("Verify update job",()->
                assertThat(response.getJob()).isEqualTo("nuclear safety inspector"));

    }

    @Test
    void patchWithountBodyTest() {
        step("Verify status code withount request body",()->
                given(userRequestSpec)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec));
    }

    @Test
    void patchAddWifeTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        data.setJob("nuclear safety inspector");
        data.setWife("Marge Simpson");
        UserBodyResponseModel response =
                step("Get name, job and wife data",()->
                        given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class));
        step("Verify update job",()->
                assertThat(response.getJob()).isEqualTo("nuclear safety inspector"));
        step("Verify update name",()->
                assertThat(response.getName()).isEqualTo("Homer Simpson"));
        step("Verify update wife",()->
                assertThat(response.getWife()).isEqualTo("Marge Simpson"));

    }

}
