import io.restassured.filter.Filter;
import model.UserBodyRequestModel;
import model.UserBodyResponseModel;
import org.junit.jupiter.api.Test;

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
        UserBodyResponseModel response = given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getName()).isEqualTo("Homer Simpson");
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector");
    }

    @Test
    void patchOnlyNameTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        UserBodyResponseModel response = given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getName()).isEqualTo("Homer Simpson");
    }

    @Test
    void patchOnlyJobTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setJob("nuclear safety inspector");
        UserBodyResponseModel response = given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector");

    }

    @Test
    void patchWithountBodyTest() {
        given(userRequestSpec)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec);
    }

    @Test
    void patchAddWifeTest() {
        UserBodyRequestModel data=new UserBodyRequestModel();
        data.setName("Homer Simpson");
        data.setJob("nuclear safety inspector");
        data.setWife("Marge Simpson");
        UserBodyResponseModel response = given(userRequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getJob()).isEqualTo("nuclear safety inspector");
        assertThat(response.getName()).isEqualTo("Homer Simpson");
        assertThat(response.getWife()).isEqualTo("Marge Simpson");

    }

}
