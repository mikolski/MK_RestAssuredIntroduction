package user;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateNewUser extends TestBase {
    private String users = "/users";
    String body = "{\n" +
            "\t\"name\": \"Jan Kowalski\",\n" +
            "\t\"username\": \"jkowal\",\n" +
            "\t\"email\": \"jkowal@email.biz\",\n" +
            "\t\"address\": {\n" +
            "\t\t\"street\": \"Knightsbridge\",\n" +
            "\t\t\"suite\": \"12\",\n" +
            "\t\t\"city\": \"London\",\n" +
            "\t\t\"zipcode\": \"TW28IO\",\n" +
            "\t\t\"geo\": {\n" +
            "\t\t\t\"lat\": \"-37.3159\",\n" +
            "\t\t\t\"lng\": \"81.1496\"\n" +
            "\t\t}\n" +
            "\t},\n" +
            "\t\"phone\": \"333444555\",\n" +
            "\t\"website\": \"aaa.org\",\n" +
            "\t\"company\": {\n" +
            "\t\t\"name\": \"Great Company\",\n" +
            "\t\t\"catchPhrase\": \"We are great\",\n" +
            "\t\t\"bs\": \"nothing\"\n" +
            "\t}\n" +
            "}";

    @Test
    public void ShouldCreateNewUser(){
        Response response =

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+users)
                .then().statusCode(201).
                extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("name"), "Jan Kowalski");
    }



}
