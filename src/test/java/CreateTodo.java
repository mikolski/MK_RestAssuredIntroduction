import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import org.assertj.core.api.SoftAssertions;

import static io.restassured.RestAssured.given;


public class CreateTodo extends TestBase {
    private String todos = "/todos";
    private String body = "{\n" +
            "    \"userId\": 9,\n" +
            "    \"title\": \"I should do the homework\",\n" +
            "    \"completed\": true\n" +
            "}";


    @Test
    public void shouldCreateTodo(){
        Response response =
            given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+todos)
                .then()
                .statusCode(201)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonPath.get("userId").toString()).isEqualTo("9");
        softAssertions.assertThat(jsonPath.get("title").toString()).isEqualTo("I should do the homework");
        softAssertions.assertThat(jsonPath.get("completed").toString()).isEqualTo("true");
        softAssertions.assertAll();

    }
}
