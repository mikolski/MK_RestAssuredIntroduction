import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUsers extends TestBase{

    private String users = "/users";

    String fullBody = "{\n" +
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

    String partBody = "{\n" +
            "\t\"name\": \"Jan Kowalski\",\n" +
            "\t\"username\": \"jkowal\"\n" +
            "\t}\n";

    @Test
    public void shouldUpdatePostWithFullBody(){
        Response response =

        given()
                .pathParam("userId","1")
                .body(fullBody)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL+users+"/{userId}")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("name"), "Jan Kowalski");
        Assert.assertEquals(jsonPath.get("email"), "jkowal@email.biz");
        Assert.assertEquals(jsonPath.get("company.name"), "Great Company");
    }

    @Test
    public void shouldUpdatePostWithLimitedBody(){
        Response response =

                given()
                        .pathParam("userId","1")
                        .body(partBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .put(BASE_URL+users+"/{userId}")
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("name"), "Jan Kowalski");
        Assert.assertNull(jsonPath.get("email"));
        Assert.assertNull(jsonPath.get("company.name"));
        //Assert.assertEquals(jsonPath.get("email"), "Sincere@april.biz");
        //Assert.assertEquals(jsonPath.get("company.name"), "Romaguera-Crona");
    }
}
