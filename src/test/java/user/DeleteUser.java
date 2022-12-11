package user;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser extends TestBase {
    private String users = "/users";

    @Test
    public void shouldGetFirstUserAndValidateWithPathVariable(){
        given()
                .pathParam("userId","1")
                .when()
                .delete(BASE_URL+users+"/{userId}")
                .then()
                .statusCode(200);
    }
}
