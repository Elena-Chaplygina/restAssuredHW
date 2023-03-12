import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {


    @Test
    void patchNameAndJobTest() {
        String data = "{ \"name\": \"Homer Simpson\", \"job\": \"nuclear safety inspector\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Homer Simpson"))
                .body("job", is("nuclear safety inspector"));
    }

    @Test
    void patchOnlyNameTest() {
        String data = "{ \"name\": \"Homer Simpson\"}";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Homer Simpson"));
    }

    @Test
    void patchOnlyJobTest() {
        String data = "{ \"job\": \"nuclear safety inspector\"}";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("nuclear safety inspector"));
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
        String data = "{ \"name\": \"Homer Simpson\", \"job\": \"nuclear safety inspector\" ,\"wife\": \"Marge Simpson\"}";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("wife", is("Marge Simpson"));

    }

}
