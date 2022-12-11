package user;

import base.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUsers extends TestBase {

private String users = "/users";

    @Test
    public void shouldGetAllUsers(){
        when()
                .get(BASE_URL+users)
                .then()
                .statusCode(200);

    }

    @Test
    public void shouldGetFirstUser(){
        when()
                .get(BASE_URL+users+"/1")
                .then()
                .statusCode(200);

    }

    @Test
    public void shouldGetFirstUserAndValidate(){
        Response response =

            when()
                    .get(BASE_URL+users+"/1")
                    .then()
                    .statusCode(200)
                    .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("address.geo.lat"), "-37.3159");

    }

    @Test
    public void shouldGetFirstUserAndValidateWithPathVariable(){
        given()
                .pathParam("userId","1")
                .when()
                .get(BASE_URL+users+"/{userId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFirstUserWithQueryParam(){
        Response response =

        given()
                .queryParam("username","Breta")
                .when()
                .get(BASE_URL+users)
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertFalse(jsonPath.get().toString().equals("[]"));

        Assert.assertEquals(jsonPath.get("[0].id").toString(), "1");
    }

}
